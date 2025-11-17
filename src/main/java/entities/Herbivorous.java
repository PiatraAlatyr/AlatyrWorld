package entities;

import enums.Fauna;
import world.LandTile;

public class Herbivorous extends Animal {

    public Herbivorous(Fauna species, String emoji, int weightInGrams, int maxPopulation, int speed, int maxSatiety) {
        super(species, emoji, weightInGrams, maxPopulation, speed, maxSatiety);
    }

    public void eat(LandTile tile) {
        if (!isEaten()) {
            if (isHerePlantFood(tile)) {
                eatPlantFood(tile);
            }

            if (isDeadFromStarving()) {
                setEaten(true);
            }
        }
    }
}
