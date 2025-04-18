import services.WorldBuilder;

import static world.Island.animalCounter;
import static world.Island.plantCounter;

public class main {
    public static void main(String[] args) {
        WorldBuilder worldBuilder = new WorldBuilder(10, 10);

        System.out.println("Остров создан!");
        System.out.println(animalCounter);
        System.out.println(plantCounter);
        System.out.println();

        for (int i = 0; i < 100; i++) {
            long start = System.currentTimeMillis();

            worldBuilder.getIsland().tick();

            long end = System.currentTimeMillis();
            long total = end-start;
            System.out.println("Время выполнения " + total);
            System.out.println();
        }
    }
}
