package game;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataLoader {
    public ArrayList<Location> Locations;

    public static DataLoader loadGame(String resourcePath) {
        Gson gson = new Gson();

        try (InputStream is = DataLoader.class.getResourceAsStream(resourcePath)) {

            if (is == null) {
                throw new IllegalStateException("Resource wasn't found: " + resourcePath);
            }
            return gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), DataLoader.class);
        } catch (Exception e) {
            throw new RuntimeException("Error when loading JSON fle: " + e.getMessage());
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


}
