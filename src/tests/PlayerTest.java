package tests;

import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro třídu Player.
 * Testuje základní funkce hráče jako inventář a lokaci.
 *
 * @author Patrik Říha
 */
class PlayerTest {
    Player player;
    Location location;

    @BeforeEach
    void setUp() {
        player = new Player();
        location = new Location();
    }

    @Test
    void getLocation() {
        location.setId("forsaken_town");
        location.setName("Forsaken town");
        location.setDescription("An old, dilapidated town");
        player.updateLocation(location);

        assertSame(location, player.getLocation());
    }

    @Test
    void addToInventory() {
        player.addToInventory("sword");
        assertTrue(player.getInventory().contains("sword"));
    }

    @Test
    void removeFromInventory() {
        player.addToInventory("glasses");
        player.removeFromInventory("glasses");
        assertFalse(player.getInventory().contains("glasses"));
    }

    @Test
    void addToAltarItems() {
        player.addToAltarItems("healing_elixir");
        assertTrue(player.getAltarItems().contains("healing_elixir"));
    }

    @Test
    void isGameWon() {
        assertFalse(player.isGameWon());
        player.setGameWon(true);
        assertTrue(player.isGameWon());
    }
}
