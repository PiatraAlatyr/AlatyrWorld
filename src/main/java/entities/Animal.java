package entities;

import enums.Fauna;
import services.Eatable;
import services.Rations;
import world.LandTile;
import services.FaunaFactory;

import java.util.*;

import static services.MigrationService.getDestination;


public abstract class Animal implements Eatable {

    Random random = new Random();
    HashMap<Eatable, Integer> ration;
    private final Fauna species;
    private final String emoji;
    private final int weightInGrams;
    private final int maxPopulation;
    private final int speed;
    private final int maxSatiety;
    private int currentSatiety;
    private boolean isEaten = false;
    int starvingRate;

    protected Animal(Fauna species, String emoji, int weightInGrams, int maxPopulation, int speed, int maxSatiety) {
        this.species = species;
        this.emoji = emoji;
        this.weightInGrams = weightInGrams;
        this.maxPopulation = maxPopulation;
        this.speed = speed;
        this.maxSatiety = maxSatiety;
        this.currentSatiety = maxSatiety/2;
        this.ration = Rations.getMenu().chooser(species);
        this.starvingRate = - getMaxSatiety()/100;
    }

    public Fauna getSpecies() {
        return species;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxSatiety() {
        return maxSatiety;
    }

    public int getCurrentSatiety() {
        return currentSatiety;
    }

    public void setCurrentSatiety(int addedSatiety) {
        if (addedSatiety != 0) {
            currentSatiety += addedSatiety;
        } else {
            currentSatiety = 0;
        }
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    @Override
    public String toString() {
        return species + " " + emoji;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return species == animal.species;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(species);
    }

    public void reproduce(LandTile tile) {
        if (currentSatiety >= maxSatiety && isHereReadyMate(tile) && isHereSpace(tile)) {
                List<Animal> mateList = tile.animals.stream()
                        .filter(this::equals)
                        .filter(mate -> mate.getCurrentSatiety() >= maxSatiety)
                        .toList();

                Animal mate = mateList.get(random.nextInt(mateList.size()));

                mate.setCurrentSatiety(1);
                setCurrentSatiety(1);
                tile.animals.add(FaunaFactory.getAnimal(this.species));
            }

    }

    private boolean isHereSpace(LandTile tile) {
        return tile.animals.stream()
                .filter(this::equals)
                .count() < getMaxPopulation();
    }

    private boolean isHereReadyMate(LandTile tile) {
        return tile.animals.stream()
                .filter(this::equals)
                .filter( mate -> mate != this)
                .filter(mate -> mate.getCurrentSatiety() >= maxSatiety)
                .anyMatch(animal -> animal.equals(this));
    }

    public boolean isHereAnimalFood(LandTile landTile) {
        Set<Eatable> rationSpecies = ration.keySet();
        return landTile.animals.stream()
                .filter(animal -> !animal.isEaten())
                .map(Animal::getSpecies)
                .anyMatch(rationSpecies::contains);
    }

    public boolean isHerePlantFood(LandTile landTile) {
        return landTile.plants.stream()
                .filter(plant -> !plant.isEaten())
                .distinct()
                .anyMatch(plant -> ration.containsKey(plant.getSpecies()));
    }

    void eatPlantFood(LandTile tile) {
        List<Plant> plantList = tile.plants.stream()
                .filter(plant -> ration.containsKey(plant.getSpecies()))
                .toList();

        Plant plant;
        do {
            plant = plantList.get(random.nextInt(plantList.size()));
        } while (plant.isEaten());

        int plantWeight = plant.getWeightInGrams();
        int appetite = getMaxSatiety() - getCurrentSatiety();

        if (plantWeight > appetite) {
            plant.setWeightInGrams(-appetite);
        } else {
            appetite = plantWeight;
            plant.setEaten(true);
        }
        setCurrentSatiety(appetite);
    }

    boolean eatAnimalFood(LandTile tile) {
        List<Animal> animalList = tile.animals.stream()
                .filter(animal -> ration.containsKey(animal.getSpecies()))
                .toList();

        Animal animal;
        do {
            animal = animalList.get(random.nextInt(animalList.size()));
        } while (animal.isEaten());

        if (random.nextInt(100) < ration.get(animal.getSpecies())) {
            animal.setEaten(true);
            setCurrentSatiety(animal.getWeightInGrams());
            return false;
        } else
            return true;
    }

    boolean isDeadFromStarving() {
        return currentSatiety <= 0;
    }

    public boolean migrate(LandTile tile) {
        LandTile newHome = getDestination(tile, this);

        if (newHome != null) {
            tile.animals.remove(this);
            newHome.migrationList.add(this);
            return true;
        } else return false;
    }

    public abstract void eat(LandTile landTile);
}
