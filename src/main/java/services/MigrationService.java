package services;

import entities.Animal;
import enums.Directions;
import world.LandTile;
import java.util.ArrayList;
import java.util.List;

import static services.RandomService.getRandomService;
import static world.Island.getIsland;

public class MigrationService {

    public static LandTile getDestination(LandTile tile, Animal animal) {
        List<Directions> directionsList = new ArrayList<>(List.of(Directions.values()));
        int random;
        Directions direction;
        LandTile destination;

        do {
            random = getRandomService().random.nextInt(0, directionsList.size());
            direction = directionsList.remove(random);
            destination = getAccessibleTile(tile, animal.getSpeed(), direction);
        } while (!directionsList.isEmpty() && isTileOverpopulated(destination, animal));

        return destination;
    }

    private static LandTile getAccessibleTile(LandTile tile, int speed, Directions direction) {
        int x = tile.getX();
        int y = tile.getY();
        LandTile[][] islandMap = getIsland().getIslandMap();
        LandTile landTile = null;

        switch (direction) {
            case UP -> {
                do {
                    if(isValid(x + speed, y)) {
                        landTile = islandMap[x + speed][y];
                        break;
                    } else speed--;
                } while (speed > 1);
            }
            case RIGHT -> {
                do {
                    if(isValid(x, y + speed)) {
                        landTile = islandMap[x][y + speed];
                        break;
                    } else speed--;
                } while (speed > 0);
            }
            case DOWN -> {
                do {
                    if(isValid(x - speed, y)) {
                        landTile = islandMap[x - speed][y];
                        break;
                    } else speed--;
                } while (speed > 0);
            }
            case LEFT -> {
                do {
                    if(isValid(x, y - speed)) {
                        landTile = islandMap[x][y - speed];
                        break;
                    } else speed--;
                } while (speed > 0);
            }
        }
        return landTile;
    }

    private static boolean isValid(int x, int y) {
        int mapWidth = getIsland().getIslandMap().length;
        int mapHeight = getIsland().getIslandMap()[0].length;

        return y >= 0 && y < mapHeight && x >= 0 && x < mapWidth;
    }

    private static boolean isTileOverpopulated(LandTile tile, Animal animal) {
        if (tile == null) {
            return true;
        } else {
            return (tile.animals.stream()
                    .filter(animal::equals)
                    .count() +
                    tile.migrationList.stream()
                            .filter(animal::equals)
                            .count())
                    > animal.getMaxPopulation();
        }
    }
}
