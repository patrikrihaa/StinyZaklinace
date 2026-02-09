package tests;

import commands.TakeItem;
import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro příkaz TakeItem.
 * Testuje sbírání předmětů z lokací.
 *
 * @author Patrik Říha
 */
class TakeItemTest {

    Player player;
    Location location;
    TakeItem takeItem;

    private static class FakeWorld extends DataLoader {
        private final HashMap<String, Item> items = new HashMap<>();

        void addItem(Item i) {
            items.put(i.getId(), i);
        }

        @Override
        public Item findItem(String id) {
            Item i = items.get(id);
            if (i == null) {
                throw new IllegalArgumentException("Item not found: " + id);
            }
            return i;
        }
    }

    FakeWorld fakeWorld;

    @BeforeEach
    void setUp() {
        player = new Player();

        location = new Location();
        location.setId("room");
        location.setName("Simple Room");
        location.setDescription("A simple room.");

        ArrayList<String> items = new ArrayList<>();
        items.add("healing_elixir");
        items.add("glasses");
        location.setItems(items);

        player.updateLocation(location);

        Item sword = new Item();
        sword.setId("healing_elixir");
        sword.setName("Healing Elirir");

        Item potion = new Item();
        potion.setId("glasses");
        potion.setName("Glasses");

        fakeWorld = new FakeWorld();
        fakeWorld.addItem(sword);
        fakeWorld.addItem(potion);

        takeItem = new TakeItem(player, fakeWorld);
    }

    @Test
    void executeWithoutArgument() {
        String result = takeItem.execute("take");
        assertTrue(result.contains("specify"));
    }

    @Test
    void executeItemNotInLocation() {
        String result = takeItem.execute("take rusty_key");
        assertTrue(result.contains("available"));
    }

    @Test
    void executeSuccessful() {
        String result = takeItem.execute("take healing_elixir");

        assertTrue(player.getInventory().contains("healing_elixir"));
        assertFalse(location.getItems().contains("healing_elixir"));
        assertTrue(result.contains("picked up"));
    }

    @Test
    void executeMultipleItems() {
        takeItem.execute("take healing_elixir");
        takeItem.execute("take glasses");

        assertTrue(player.getInventory().contains("glasses"));
        assertTrue(player.getInventory().contains("healing_elixir"));
        assertEquals(0, location.getItems().size());
    }

    @Test
    void exitReturnsFalse() {
        assertFalse(takeItem.exit());
    }
}
