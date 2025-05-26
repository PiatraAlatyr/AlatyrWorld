package world;

import entities.Animal;
import entities.Plant;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newWorkStealingPool;


public class Island {

    private static Island instance;

    public static Island createIsland(int x, int y) {
        if (instance == null) {
            instance = new Island(x, y);
        }
        return instance;
    }

    public static Island getIsland() {
        return instance;
    }

    private final LandTile[][] islandMap;

    int tickCounter =0;
    public static Map<Animal, Integer> animalCounter = new ConcurrentHashMap<>();
    public static Map<Plant, Integer> plantCounter = new ConcurrentHashMap<>();

    private Island(int x, int y) {
        islandMap = new LandTile[x][y];

        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap[i].length; j++) {
                islandMap[i][j] = new LandTile(i, j);
            }
        }
    }

    public LandTile[][] getIslandMap() {
        return islandMap;
    }

    public void tick() {
        animalCounter.clear();
        plantCounter.clear();
        tickCounter++;

        ExecutorService executorService = newWorkStealingPool();
        try {
            Arrays.stream(islandMap)
                    .flatMap(Arrays::stream)
                    .forEach(executorService::execute);

            executorService.shutdown();
            if (executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                printer();
            }
        } catch (InterruptedException _) {
            // обработка
        } finally {
            if (!executorService.isShutdown()) {
                executorService.shutdownNow();
            }
        }
    }

    public static synchronized void counter(List<Animal> animals, List<Plant> plants) {
        for (Animal animal : animals) {
            if (animalCounter.containsKey(animal)) {
                animalCounter.put(animal, animalCounter.get(animal) + 1);
            } else {
                animalCounter.put(animal, 1);
            }
        }
        for (Plant plant : plants) {
            if (plantCounter.containsKey(plant)) {
                plantCounter.put(plant, plantCounter.get(plant) + 1);
            } else {
                plantCounter.put(plant, 1);
            }
        }
    }

    public void printer() {
        System.out.println("Эра " + tickCounter);
        System.out.println(animalCounter);
        System.out.println(plantCounter);
    }
}
