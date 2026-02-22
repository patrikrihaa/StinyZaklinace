package tests;

import game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro třídu Location.
 * Testuje vlastnosti a chování lokací.
 *
 * @author Patrik Říha
 */
class LocationTest {
    Location location;

    @BeforeEach
    void setUp() {
        location = new Location();
    }

    @Test
    void isLocked() {
        location.setLocked(true);
        assertTrue(location.isLocked());
        location.setLocked(false);
        assertFalse(location.isLocked());
    }

    @Test
    void getNextLocations() {
        ArrayList<String> neighbors = new ArrayList<>();
        neighbors.add("forsaken_town");
        neighbors.add("sunny_glade");
        location.setNextLocations(neighbors);

        assertEquals(2, location.getNextLocations().size());
        assertTrue(location.getNextLocations().contains("forsaken_town"));
    }
}
