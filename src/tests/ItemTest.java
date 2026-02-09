package tests;

import game.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro třídu Item.
 * Testuje vlastnosti předmětů.
 *
 * @author Patrik Říha
 */
class ItemTest {
    Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
    }

    @Test
    void getId() {
        item.setId("healing_elixir");
        assertEquals("healing_elixir", item.getId());
    }

    @Test
    void getName() {
        item.setName("Glasses");
        assertEquals("Glasses", item.getName());
    }

    @Test
    void getDescription() {
        item.setDescription("A wand that allows you to use magic");
        assertEquals("A wand that allows you to use magic", item.getDescription());
    }

    @Test
    void setId() {
        item.setId("healing_elixir");
        assertEquals("healing_elixir", item.getId());
    }

    @Test
    void setName() {
        item.setName("Spell Fragment");
        assertEquals("Spell Fragment", item.getName());
    }
}
