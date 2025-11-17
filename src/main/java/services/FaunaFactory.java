package services;

import entities.Omnivorous;
import enums.Fauna;
import entities.Animal;
import entities.Carnivorous;
import entities.Herbivorous;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FaunaFactory {
    private static final String CONFIG_PATH = "src/main/resources/fauna_config.json";
    private static final Map<Fauna, JSONObject> config = new EnumMap<>(Fauna.class);
    static {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            for (Object key : obj.keySet()) {
                Fauna fauna = Fauna.valueOf((String) key);
                config.put(fauna, (JSONObject) obj.get(key));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Error reading plant configuration: " + e.getMessage());
        }
    }

    public static Animal getAnimal(Fauna species) {
        JSONObject params = config.get(species);
        String emoji = (String) params.get("emoji");
        long weight = (long) params.get("weightInGrams");
        long maxPopulation = (long) params.get("maxPopulation");
        long speed = (long) params.get("speed");
        long maxSatiety = (long) params.get("maxSatiety");
        boolean carnivorous = (boolean) params.get("carnivorous");
        if (carnivorous) {
            return new Carnivorous(species, emoji, (int)weight, (int)maxPopulation, (int)speed, (int)maxSatiety);
        } else {
            Rations menu = Rations.getMenu();
            if( menu.isOmnivore(species)) {
                return new Omnivorous(species, emoji, (int) weight, (int) maxPopulation, (int) speed, (int) maxSatiety);
            } else {
                return new Herbivorous(species, emoji, (int) weight, (int) maxPopulation, (int) speed, (int) maxSatiety);
            }
        }
    }

    public static int getMaxPopulation(Fauna species) {
        JSONObject params = config.get(species);
        return ((Long) params.get("maxPopulation")).intValue();
    }
}
