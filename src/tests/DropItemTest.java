package tests;

import commands.DropItem;
import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro příkaz DropItem.
 * Testuje zahazování předmětů z inventáře.
 *
 * @author Patrik Říha
 */
class DropItemTest {
    Player player;
    Location location;
    DropItem dropItemCommand;
    DataLoader world;

    @BeforeEach
    void setUp() {
        player = new Player();
        location = new Location();
        location.setId("rotting_marshlands");
        location.setName("Rotting Marshlands");
        location.setItems(new ArrayList<>());
        player.setLocation(location);

        world = new DataLoader();
        world.Items = new ArrayList<>();

        Item crystal = new Item();
        crystal.setId("crystal_light");
        crystal.setName("Crystal of Light");
        crystal.setDescription("A magical crystal.");
        world.Items.add(crystal);

        Item key = new Item();
        key.setId("rusty_key");
        key.setName("Rusty Key");
        key.setDescription("An old key.");
        world.Items.add(key);

        Item wand = new Item();
        wand.setId("magic_wand");
        wand.setName("Magic Wand");
        wand.setDescription("A powerful wand.");
        world.Items.add(wand);

        Item glasses = new Item();
        glasses.setId("glasses");
        glasses.setName("Glasses");
        glasses.setDescription("A glasses.");
        world.Items.add(glasses);

        dropItemCommand = new DropItem(player, world);
    }

    @Test
    void DropItemSuccessfully() {
        player.addToInventory("crystal_light");

        String result = dropItemCommand.execute("drop crystal_light");

        assertTrue(result.contains("dropped"));
        assertEquals(1, player.getInventorySize());
        assertFalse(player.getInventory().contains("crystal_light"));
        assertTrue(location.getItems().contains("crystal_light"));
    }

    @Test
    void DropItemNotInInventory() {
        String result = dropItemCommand.execute("drop crystal_light");

        assertTrue(result.contains("can't drop"));
    }

    @Test
    void DropMultipleItems() {
        player.addToInventory("crystal_light");
        player.addToInventory("rusty_key");
        player.addToInventory("glasses");
        assertEquals(4, player.getInventorySize());

        dropItemCommand.execute("drop crystal_light");
        dropItemCommand.execute("drop rusty_key");

        assertEquals(2, player.getInventorySize());
        assertTrue(player.getInventory().contains("glasses"));
    }

    @Test
    void DropItemCreatesSpaceInInventory() {
        for (int i = 0; i < 9; i++) {
            player.addToInventory("item_" + i);
        }

        assertTrue(player.isInventoryFull());
        assertEquals(0, player.getRemainingInventorySpace());

        dropItemCommand.execute("drop item_0");

        assertTrue(player.isInventoryFull());
        assertEquals(10, player.getInventorySize());
        assertEquals(0, player.getRemainingInventorySpace());
    }

    @Test
    void DropMagicWand() {
        assertTrue(player.getInventory().contains("magic_wand"));
        dropItemCommand.execute("drop magic_wand");

        assertEquals(0, player.getInventorySize());
    }

    @Test
    void DropItemThenPickItUpAgain() {
        player.addToInventory("crystal_light");

        dropItemCommand.execute("drop crystal_light");
        assertFalse(player.getInventory().contains("crystal_light"));
        assertTrue(location.getItems().contains("crystal_light"));

        player.addToInventory("crystal_light");
        location.getItems().remove("crystal_light");

        assertTrue(player.getInventory().contains("crystal_light"));
        assertFalse(location.getItems().contains("crystal_light"));
    }

    @Test
    void DropAllItemsFromFullInventory() {
        for (int i = 0; i < 9; i++) {
            player.addToInventory("item_" + i);
        }

        assertEquals(10, player.getInventorySize());

        for (int i = 0; i < 9; i++) {
            dropItemCommand.execute("drop item_" + i);
        }

        assertEquals(0, location.getItems().size());
    }
}