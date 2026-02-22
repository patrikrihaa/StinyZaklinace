package tests;

import game.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testovací třída pro třídu Character.
 * Testuje vlastnosti postav a jejich interakce.
 *
 * @author Patrik Říha
 */
class CharacterTest {
    Character character;

    @BeforeEach
    void setUp() {
        character = new Character();
    }

    @Test
    void getId() {
        character.setId("ancient_oak");
        assertEquals("ancient_oak", character.getId());
    }

    @Test
    void getLocation() {
        character.setLocation("forsaken_town");
        assertEquals("forsaken_town", character.getLocation());
    }
}
