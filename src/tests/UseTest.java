package tests;

import commands.Use;
import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro příkaz Use.
 * Testuje použití předmětů v různých kontextech.
 *
 * @author Patrik Říha
 */
class UseTest {

    Player player;
    Location location;
    Use use;

    private static class FakeWorld extends DataLoader {
        private final HashMap<String, Item> items = new HashMap<>();
        private final ArrayList<String> altarReqs = new ArrayList<>();

        {
            this.AltarRequirements = altarReqs;
        }

        void addItem(Item i) {
            items.put(i.getId(), i);
        }

        void addAltarRequirement(String itemId) {
            altarReqs.add(itemId);
        }

        @Override
        public Item findItem(String id) {
            Item i = items.get(id);
            if (i == null) {
                throw new IllegalArgumentException("Item not found: " + id);
            }
            return i;
        }

        @Override
        public ArrayList<game.Character> getCharactersInLocation(String locationId) {
            return new ArrayList<>();
        }
    }

    FakeWorld fakeWorld;

    @BeforeEach
    void setUp() {
        player = new Player();

        location = new Location();
        location.setId("druids_star_circle");
        location.setName("Druid's Circle");
        player.updateLocation(location);

        Item artifact = new Item();
        artifact.setId("spell_fragment");
        artifact.setName("Spell Fragment");

        fakeWorld = new FakeWorld();
        fakeWorld.addItem(artifact);
        fakeWorld.addAltarRequirement("spell_fragment");

        use = new Use(player, fakeWorld);
    }

    @Test
    void executeItemNotInInventory() {
        String result = use.execute("use glasses");
        assertTrue(result.contains("don't have"));
    }

    @Test
    void executeWinCondition() {
        player.addToInventory("spell_fragment");
        use.execute("use spell_fragment");

        assertTrue(player.hasAllAltarItems(fakeWorld.AltarRequirements));
    }
}
