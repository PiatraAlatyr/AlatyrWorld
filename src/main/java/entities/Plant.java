package entities;

import enums.Flora;
import services.Eatable;

import java.util.*;

public class Plant implements Eatable {

    private final Flora species;
    private final String emoji;
    private int weightInGrams;
    private final int maxPopulation;
    private boolean isEaten = false;

    public Plant(Flora species, String emoji, int weightInGrams, int maxPopulation) {
        this.species = species;
        this.emoji = emoji;
        this.weightInGrams = weightInGrams;
        this.maxPopulation = maxPopulation;
    }

    public Flora getSpecies() {
        return species;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(int weightChange) {
        weightInGrams += weightChange;
    }

    public int getMaxPopulation() {
        return maxPopulation;
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
        Plant plant = (Plant) o;
        return species == plant.species;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(species);
    }

}
