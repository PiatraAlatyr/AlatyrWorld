package services;

import enums.Fauna;
import enums.Flora;

import java.util.HashMap;

import static enums.Fauna.*;

public class Rations {

    private static Rations instance;

    public static synchronized Rations getMenu() {
        if (instance == null) {
            instance = new Rations();
        }
        return instance;
    }

    private Rations() {
    }

    public HashMap<Eatable, Integer> chooser(Fauna species) {
        return switch (species) {
            case WOLF -> wolfRation;
            case SNAKE -> snakeRation;
            case FOX -> foxRation;
            case BEAR -> bearRation;
            case EAGLE -> eagleRation;
            case HORSE, DEER, RABBIT, GOAT, SHEEP, CATERPILLAR, BUFFALO -> herbivorousRation;
            case MOUSE -> mouseRation;
            case BOAR -> boarRation;
            case DUCK -> duckRation;
        };
    }

    public boolean isOmnivore(Fauna species) {
        return species == MOUSE || species == BOAR || species == DUCK;
    }

    private static final HashMap<Eatable, Integer> herbivorousRation;

    static {
        herbivorousRation = new HashMap<>();
        herbivorousRation.put(Flora.GRASS, 100);
        herbivorousRation.put(Flora.BUSH, 100);
    }

    private static final HashMap<Eatable, Integer> wolfRation;

    static {
        wolfRation = new HashMap<>();
        wolfRation.put(Fauna.DEER, 15);
        wolfRation.put(Fauna.HORSE, 10);
        wolfRation.put(Fauna.RABBIT, 60);
        wolfRation.put(MOUSE, 80);
        wolfRation.put(Fauna.GOAT, 60);
        wolfRation.put(Fauna.SHEEP, 70);
        wolfRation.put(Fauna.BOAR, 15);
        wolfRation.put(Fauna.BUFFALO, 10);
        wolfRation.put(Fauna.DUCK, 40);
    }

    private static final HashMap<Eatable, Integer> snakeRation;

    static {
        snakeRation = new HashMap<>();
        snakeRation.put(Fauna.FOX, 15);
        snakeRation.put(Fauna.RABBIT, 20);
        snakeRation.put(MOUSE, 40);
        snakeRation.put(Fauna.DUCK, 10);
    }

    private static final HashMap<Eatable, Integer> foxRation;

    static {
        foxRation = new HashMap<>();
        foxRation.put(Fauna.RABBIT, 70);
        foxRation.put(MOUSE, 90);
        foxRation.put(Fauna.DUCK, 60);
        foxRation.put(Fauna.CATERPILLAR, 40);
    }

    private static final HashMap<Eatable, Integer> bearRation;

    static {
        bearRation = new HashMap<>();
        bearRation.put(Fauna.SNAKE, 80);
        bearRation.put(Fauna.HORSE, 40);
        bearRation.put(Fauna.DEER, 80);
        bearRation.put(Fauna.RABBIT, 80);
        bearRation.put(MOUSE, 90);
        bearRation.put(Fauna.GOAT, 70);
        bearRation.put(Fauna.SHEEP, 70);
        bearRation.put(Fauna.BOAR, 50);
        bearRation.put(Fauna.BUFFALO, 20);
        bearRation.put(Fauna.DUCK, 10);
    }

    private static final HashMap<Eatable, Integer> eagleRation;

    static {
        eagleRation = new HashMap<>();
        eagleRation.put(Fauna.FOX, 10);
        eagleRation.put(Fauna.RABBIT, 90);
        eagleRation.put(MOUSE, 90);
        eagleRation.put(Fauna.DUCK, 80);
    }

    private static final HashMap<Eatable, Integer> duckRation;

    static {
        duckRation = new HashMap<>();
        duckRation.put(Flora.GRASS, 100);
        duckRation.put(Fauna.CATERPILLAR, 90);
    }

    private static final HashMap<Eatable, Integer> mouseRation;

    static {
        mouseRation = new HashMap<>();
        mouseRation.put(Flora.GRASS, 100);
        mouseRation.put(Fauna.CATERPILLAR, 90);
    }

    private static final HashMap<Eatable, Integer> boarRation;

    static {
        boarRation = new HashMap<>();
        boarRation.put(Flora.GRASS, 100);
        boarRation.put(MOUSE, 50);
    }

}
