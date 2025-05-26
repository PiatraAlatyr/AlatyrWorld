package services;

import enums.Flora;
import entities.Plant;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FloraFactory {
    private static final String CONFIG_PATH = "src/main/resources/flora_config.json";
    private static final Map<Flora, JSONObject> config = new EnumMap<>(Flora.class);
    static {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            for (Object key : obj.keySet()) {
                Flora flora = Flora.valueOf((String) key);
                config.put(flora, (JSONObject) obj.get(key));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Ошибка чтения конфигурации растений: " + e.getMessage());
        }
    }

    public static Plant getPlant(Flora species) {
        JSONObject params = config.get(species);
        String emoji = (String) params.get("emoji");
        long weight = (long) params.get("weightInGrams");
        long maxPopulation = (long) params.get("maxPopulation");
        return new Plant(species, emoji, (int)weight, (int)maxPopulation);
    }

    public static int getMaxPopulation(Flora species) {
        JSONObject params = config.get(species);
        return ((Long) params.get("maxPopulation")).intValue();
    }
}
