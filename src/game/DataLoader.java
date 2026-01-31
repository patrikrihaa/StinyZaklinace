package game;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataLoader {
    public ArrayList<Location> Locations;
    public ArrayList<Item> Items;
    public ArrayList<Character> Characters;
//    public ArrayList<Quest> quests;

    public static DataLoader loadGame(String resourcePath) {
        Gson gson = new Gson();

        try (InputStream Is = DataLoader.class.getResourceAsStream(resourcePath)) {

            if (Is == null) {
                throw new IllegalStateException("Resource wasn't found: " + resourcePath);
            }
            return gson.fromJson(new InputStreamReader(Is, StandardCharsets.UTF_8), DataLoader.class);
        } catch (Exception e) {
            throw new RuntimeException("Error when loading JSON fle: ");
        }

    }

    public Location findLocation(String id) {
        for (Location l : Locations) {
            if (l.getId().equals(id)){
                return l;
            }
        }
        throw new IllegalArgumentException("Location with this id doesn't exist");
    }

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


}
