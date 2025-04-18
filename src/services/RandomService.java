package services;

import java.util.concurrent.ThreadLocalRandom;

public class RandomService {

    static RandomService randomService;
    ThreadLocalRandom random;

    public RandomService(ThreadLocalRandom random) {
        this.random = random;
    }

    public static RandomService getRandomService() {
        if (randomService == null) {
            synchronized (RandomService.class) {
                randomService = new RandomService(ThreadLocalRandom.current());
            }
        }
        return randomService;
    }
}