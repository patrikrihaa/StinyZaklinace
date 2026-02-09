package tests;

import commands.LookAround;
import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro příkaz LookAround.
 * Testuje zobrazení informací o lokaci.
 *
 * @author Patrik Říha
 */
class LookAroundTest {
    Player player;
    Location location;
    LookAround lookAroundCommand;

    @BeforeEach
    void setUp() {
        player = new Player();
        location = new Location();
        location.setId("rotting_marshlands");
        location.setName("Rotting Marshlands");
        location.setDescription("Starting location full of dangerous swamps and toxic vapors.");
        location.setItems(new ArrayList<>());
        player.setLocation(location);

        lookAroundCommand = new LookAround(player);
    }

    @Test
    void LookAroundEmptyLocation() {
        String result = lookAroundCommand.execute("look");

        assertTrue(result.contains("no items"));
    }

    @Test
    void LookAroundWithSingleItem() {
        location.getItems().add("crystal_light");

        String result = lookAroundCommand.execute("look");

        assertTrue(result.contains("dangerous swamps"));
        assertTrue(result.contains("crystal_light"));
    }


    @Test
    void LookAroundShowsHelpText() {
        location.getItems().add("crystal_light");
        String result = lookAroundCommand.execute("look");

        assertTrue(result.contains("take"));
    }

    @Test
    void LookAroundAfterPickingUpItem() {
        location.setItems(new ArrayList<>(Arrays.asList("crystal_light", "rusty_key")));

        String Before = lookAroundCommand.execute("look");
        assertTrue(Before.contains("crystal_light"));
        assertTrue(Before.contains("rusty_key"));

        location.getItems().remove("crystal_light");

        String After = lookAroundCommand.execute("look");
        assertTrue(After.contains("rusty_key"));
    }


    @Test
    void LookAroundInDifferentLocation() {
        Location l = new Location();
        l.setId("forsaken_town");
        l.setName("Forsaken Town");
        l.setDescription("Old abandoned town with crumbling buildings.");
        l.setItems(new ArrayList<>(Arrays.asList("healing_elixir")));

        player.setLocation(l);

        String result = lookAroundCommand.execute("look");

        assertTrue(result.contains("abandoned"));
        assertTrue(result.contains("healing_elixir"));
        assertFalse(result.contains("rooting_marshlands"));
    }


    @Test
    void LookAroundWithNullItems() {
        location.setItems(null);

        String result = lookAroundCommand.execute("look");

        assertTrue(result.contains("no items"));
    }
}