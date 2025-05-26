package world;

import enums.Fauna;
import enums.Flora;
import entities.Animal;
import entities.Plant;
import services.FaunaFactory;
import services.FloraFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class LandTile implements Runnable {
    Random random = new Random();

    int x;
    int y;
    public List<Animal> animals = new CopyOnWriteArrayList<>();
    public List<Plant> plants = new CopyOnWriteArrayList<>();
    public List<Animal> migrationList = new CopyOnWriteArrayList<>();

    public LandTile(int xCoordinate, int yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
        growFlora();
        createFauna();
        Island.counter(animals, plants);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void createFauna() {
        for (Fauna species: Fauna.values()) {
            int population = random.nextInt(0, FaunaFactory.getMaxPopulation(species) + 1);

            for (int i = 0; i < population; i++) {
                animals.add(FaunaFactory.getAnimal(species));
            }
            Collections.shuffle(animals);
        }
    }

    private void growFlora() {
        for (Flora species: Flora.values()) {
            int populationEntry = (int) plants.stream()
                    .filter(plant -> plant.getSpecies().equals(species))
                    .count();
            int populationMax = random.nextInt(0, FloraFactory.getMaxPopulation(species) + 1 - populationEntry);

            for (int i = 0; i < populationMax; i++) {
                plants.add(FloraFactory.getPlant(species));
            }
        }
    }

    private void eating() {
        for(Animal animal : animals) {
            animal.eat(this);
        }
        eatingCleanUp();
    }

    private void eatingCleanUp() {
        for (int i = 0; i < animals.size(); i++) {
            if(animals.get(i).isEaten()) {
                animals.remove(animals.get(i));
                i--;
            }
        }
        for (int i = 0; i < plants.size(); i++) {
            if(plants.get(i).isEaten()) {
                plants.remove(plants.get(i));
                i--;
            }
        }
    }

    private void breeding() {
        List<Animal> temp = new ArrayList<>(animals);
        for(Animal animal : temp) {
            animal.reproduce(this);
        }
    }

    private void migrate() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).migrate(this)) {
                i--;
            }
        }
        animals.addAll(migrationList);
        migrationList.clear();
    }

    @Override
    public void run() {
        eating();
        breeding();
        migrate();
        growFlora();

        Island.counter(animals, plants);
    }
}
