package tests;

import commands.Movement;
import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro příkaz Movement.
 * Testuje pohyb mezi lokacemi.
 *
 * @author Patrik Říha
 */
class MovementTest {
    Player player;
    Location location;
    Location location2;
    Movement movement;

    private static class FakeWorld extends DataLoader {
        private final HashMap<String, Location> map = new HashMap<>();

        void add(Location loc) {
            map.put(loc.getId(), loc);
        }

        @Override
        public Location findLocation(String id) {
            Location loc = map.get(id);
            if (loc == null) {
                throw new IllegalArgumentException("Location not found: " + id);
            }
            return loc;
        }
    }

    FakeWorld fakeWorld;

    @BeforeEach
    void setUp() {
        player = new Player();

        location = new Location();
        location.setId("sunny_glade");
        location.setName("Sunny Glade");
        location.setDescription("A starting location.");
        location.setLocked(false);
        ArrayList<String> neighbors = new ArrayList<>();
        neighbors.add("forsaken_town");
        location.setNextLocations(neighbors);

        location2 = new Location();
        location2.setId("forsaken_town");
        location2.setName("Forsaken town");
        location2.setDescription("An abandoned location.");
        location2.setLocked(false);
        location2.setNextLocations(new ArrayList<>());

        fakeWorld = new FakeWorld();
        fakeWorld.add(location);
        fakeWorld.add(location2);

        player.updateLocation(location);
        movement = new Movement(player, fakeWorld);
    }

    @Test
    void executeValidMovement() {
        movement.execute("go forsaken_town");
        assertEquals("forsaken_town", player.getLocation().getId());
    }

    @Test
    void executeInvalidLocation() {
        String result = movement.execute("go sunny_glade");
        assertTrue(result.contains("can't move"));
    }
}
