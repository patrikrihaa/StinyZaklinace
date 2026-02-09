package game;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Načítá herní data ze JSON souboru.
 * Poskytuje metody pro vyhledávání lokací, předmětů, postav a questů podle jejich ID.
 *
 * @author Patrik Říha
 */
public class DataLoader {
    public ArrayList<Location> Locations;
    public ArrayList<Item> Items;
    public ArrayList<Character> Characters;
    public ArrayList<Quest> Quests;
    public ArrayList<String> AltarRequirements;

    public static DataLoader loadGame(String resourcePath) {
        Gson gson = new Gson();

        try (InputStream Is = DataLoader.class.getResourceAsStream(resourcePath)) {

            if (Is == null) {
                throw new IllegalStateException("Resource wasn't found: " + resourcePath);
            }
            return gson.fromJson(new InputStreamReader(Is, StandardCharsets.UTF_8), DataLoader.class);
        } catch (Exception e) {
            throw new RuntimeException("Error when loading JSON file: " + e.getMessage());
        }

    }

    /**
     * Najde lokaci pomocí její ID.
     *
     * @param id ID dané lokace
     * @return lokace s daným ID
     */
    public Location findLocation(String id) {
        if (Locations == null) {
            throw new IllegalArgumentException("No location loaded in game");
        }
        for (Location l : Locations) {
            if (l.getId().equals(id)){
                return l;
            }
        }
        throw new IllegalArgumentException("Location with id '" + id + "' doesn't exist");
    }

    /**
     * Najde předmět pomocí jeho ID
     * @param id ID daného itemu
     * @return Item s daným ID
     */
    public Item findItem(String id) {
        if (Items == null) {
            throw new IllegalArgumentException("No items loaded in game");
        }
        for (Item item : Items) {
            if (item.getId().equals(id)){
                return item;
            }
        }
        throw new IllegalArgumentException("Item with id '" + id + "' doesn't exist");
    }

    /**
     * Najde Character (NPC) pomocí jejího ID
     * @param id ID daného NPC
     * @return Character s daným ID
     */
    public Character findCharacter(String id) {
        if (Characters == null) {
            throw new IllegalArgumentException("No characters loaded in game");
        }
        for (Character character : Characters) {
            if (character.getId().equals(id)){
                return character;
            }
        }
        throw new IllegalArgumentException("Character with id '" + id + "' doesn't exist");
    }

    /**
     * Najde quest pomocí jeho ID
     * @param id ID daného questu
     * @return Quest s daným ID
     */
    public Quest findQuest(String id) {
        if (Quests == null) {
            throw new IllegalArgumentException("No quests loaded in game");
        }
        for (Quest quest : Quests) {
            if (quest.getId().equals(id)){
                return quest;
            }
        }
        throw new IllegalArgumentException("Quest with id '" + id + "' doesn't exist");
    }

    /**
     * Vypíše všechny Charactery z dané lokace
     * @param locationId ID dané lokace
     * @return Charactery v dané lokaci
     */
    public ArrayList<Character> getCharactersInLocation(String locationId) {
        ArrayList<Character> chars = new ArrayList<>();
        if (Characters != null) {
            for (Character c : Characters) {
                if (c.getLocation().equals(locationId)) {
                    chars.add(c);
                }
            }
        }
        return chars;
    }
}
