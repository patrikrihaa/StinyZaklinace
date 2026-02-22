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
        location.setId("sunny_glade");
        location.setName("Sunny Glade");
        location.setDescription("A pretty....");

        ArrayList<String> items = new ArrayList<>();
        items.add("healing_elixir");
        location.setItems(items);

        player.updateLocation(location);
        Item elixir = new Item();
        elixir.setId("healing_elixir");
        elixir.setName("Healing Elirir");

        fakeWorld = new FakeWorld();
        fakeWorld.addItem(elixir);

        takeItem = new TakeItem(player, fakeWorld);
    }

    @Test
    void executeItemNotInLocation() {
        String result = takeItem.execute("take rusty_key");
        assertTrue(result.contains("isn't available"));
    }

    @Test
    void executeSuccessful() {
        String result = takeItem.execute("take healing_elixir");

        assertTrue(player.getInventory().contains("healing_elixir"));
        assertFalse(location.getItems().contains("healing_elixir"));
        assertTrue(result.contains("picked up"));
    }
}
