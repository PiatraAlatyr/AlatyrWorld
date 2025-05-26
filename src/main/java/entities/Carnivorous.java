package entities;

import enums.Fauna;
import world.LandTile;

public class Carnivorous extends Animal {

    public Carnivorous(Fauna species, String emoji, int weightInGrams, int maxPopulation, int speed, int maxSatiety) {
        super(species, emoji, weightInGrams, maxPopulation, speed, maxSatiety);
    }

    public void eat(LandTile tile) {
        if (!isEaten()) {
            if (isHereAnimalFood(tile)) {
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
