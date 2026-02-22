package tests;

import game.Quest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro třídu Quest.
 * Testuje funkčnost questů a jejich dokončení.
 *
 * @author Patrik Říha
 */
class QuestTest {
    Quest quest;

    @BeforeEach
    void setUp() {
        quest = new Quest();
    }

    @Test
    void isCompleted() {
        assertFalse(quest.isCompleted());
        quest.setCompleted(true);
        assertTrue(quest.isCompleted());
    }

    @Test
    void getRequiredItem() {
        quest.setRequiredItem("rusty_key");
        assertEquals("rusty_key", quest.getRequiredItem());
    }

}
