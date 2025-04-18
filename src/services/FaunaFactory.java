package services;

import enums.Fauna;
import entities.Animal;
import entities.Carnivorous;
import entities.Herbivorous;

public class FaunaFactory {

    public static Animal getAnimal(Fauna species) {
        return switch (species) {
            case WOLF -> new Carnivorous(Fauna.WOLF, "\uD83D\uDC3A", 50_000, 30, 8, 8_000);
            case SNAKE -> new Carnivorous(Fauna.SNAKE, "\uD83D\uDC0D", 15_000, 30, 1, 3_000);
            case FOX -> new Carnivorous(Fauna.FOX,"\uD83E\uDD8A", 8_000, 30, 2, 2_000);
            case BEAR -> new Carnivorous(Fauna.BEAR,"\uD83D\uDC3B", 500_000, 5, 2, 80_000);
            case EAGLE -> new Carnivorous(Fauna.EAGLE,"\uD83E\uDD85", 6_000, 20, 3, 1_000);
            case HORSE -> new Herbivorous(Fauna.HORSE,"\uD83D\uDC0E", 400_000, 20, 4, 60_000);
            case DEER -> new Herbivorous(Fauna.DEER, "\uD83E\uDD8C", 300_000, 20, 4, 50_000);
            case RABBIT -> new Herbivorous(Fauna.RABBIT, "\uD83D\uDC07", 2_000, 150, 2, 450);
            case MOUSE -> new Herbivorous(Fauna.MOUSE, "\uD83D\uDC01", 50, 500, 1, 10);
            case GOAT -> new Herbivorous(Fauna.GOAT, "\uD83D\uDC10",60_000, 140, 3, 10_000 );
            case SHEEP -> new Herbivorous(Fauna.SHEEP, "\uD83D\uDC11", 70_000, 140, 3, 15_000);
            case BOAR -> new Herbivorous(Fauna.BOAR, "\uD83D\uDC17", 400_000, 50, 2, 50_000 );
            case BUFFALO -> new Herbivorous(Fauna.BUFFALO, "\uD83D\uDC03", 700_000, 10, 3, 100_000);
            case DUCK -> new Herbivorous(Fauna.DUCK, "\uD83E\uDD86", 1_000, 200, 4, 150);
            case CATERPILLAR -> new Herbivorous(Fauna.CATERPILLAR, "\uD83D\uDC1B", 10, 1000, 0, 10);
        };
    }

    public static int getMaxPopulation(Fauna species) {
        return getAnimal(species).getMaxPopulation();
    }
}
