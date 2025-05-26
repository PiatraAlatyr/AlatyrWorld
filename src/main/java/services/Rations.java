package services;

import enums.Fauna;
import enums.Flora;

import java.util.Map;

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

    public Map<Eatable, Integer> chooser(Fauna species) {
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

    private static final Map<Eatable, Integer> herbivorousRation = Map.of(
            Flora.GRASS, 100,
            Flora.BUSH, 100
    );

    private static final Map<Eatable, Integer> wolfRation = Map.of(
            Fauna.DEER, 15,
            Fauna.HORSE, 10,
            Fauna.RABBIT, 60,
            Fauna.MOUSE, 80,
            Fauna.GOAT, 60,
            Fauna.SHEEP, 70,
            Fauna.BOAR, 15,
            Fauna.BUFFALO, 10,
            Fauna.DUCK, 40
    );

    private static final Map<Eatable, Integer> snakeRation = Map.of(
            Fauna.FOX, 15,
            Fauna.RABBIT, 20,
            Fauna.MOUSE, 40,
            Fauna.DUCK, 10
    );

    private static final Map<Eatable, Integer> foxRation = Map.of(
            Fauna.RABBIT, 70,
            Fauna.MOUSE, 90,
            Fauna.DUCK, 60,
            Fauna.CATERPILLAR, 40
    );

    private static final Map<Eatable, Integer> bearRation = Map.of(
            Fauna.SNAKE, 80,
            Fauna.HORSE, 40,
            Fauna.DEER, 80,
            Fauna.RABBIT, 80,
            Fauna.MOUSE, 90,
            Fauna.GOAT, 70,
            Fauna.SHEEP, 70,
            Fauna.BOAR, 50,
            Fauna.BUFFALO, 20,
            Fauna.DUCK, 10
    );

    private static final Map<Eatable, Integer> eagleRation = Map.of(
            Fauna.FOX, 10,
            Fauna.RABBIT, 90,
            Fauna.MOUSE, 90,
            Fauna.DUCK, 80
    );

    private static final Map<Eatable, Integer> duckRation = Map.of(
            Flora.GRASS, 100,
            Fauna.CATERPILLAR, 90
    );

    private static final Map<Eatable, Integer> mouseRation = Map.of(
            Flora.GRASS, 100,
            Fauna.CATERPILLAR, 90
    );

    private static final Map<Eatable, Integer> boarRation = Map.of(
            Flora.GRASS, 100,
            Fauna.MOUSE, 50
    );

}
