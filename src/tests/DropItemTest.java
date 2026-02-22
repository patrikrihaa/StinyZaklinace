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
}