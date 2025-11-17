package entities;

import enums.Fauna;
import world.LandTile;

public class Omnivorous extends Animal {

    public Omnivorous(Fauna species, String emoji, int weight, int maxPopulation, int speed, int maxSatiety) {
        super(species, emoji, weight, maxPopulation, speed, maxSatiety);
    }

    public void eat(LandTile tile) {
        if (!isEaten()) {
            if (isHerePlantFood(tile)) {
                eatPlantFood(tile);
            } else if (isHereAnimalFood(tile)) {
                if (eatAnimalFood(tile)) {
                    setCurrentSatiety(starvingRate);
                }
            }

            if (isDeadFromStarving()) {
                setEaten(true);
            }
        }
    }
}
