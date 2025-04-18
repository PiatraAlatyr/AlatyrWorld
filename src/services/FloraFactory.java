package services;

import enums.Flora;
import entities.Plant;

public class FloraFactory {

    public static Plant getPlant(Flora species) {
        return switch (species) {
            case GRASS -> new Plant(Flora.GRASS, "\uD83C\uDF40", 10_000, 50);
            case BUSH -> new Plant(Flora.BUSH, "\uD83C\uDF33", 100_000, 10);
        };
    }

    public static int getMaxPopulation(Flora species) {
        return getPlant(species).getMaxPopulation();
    }
}
