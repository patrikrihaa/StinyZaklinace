package commands;

import game.Character;
import game.DataLoader;
import game.Location;
import game.Player;
import game.Quest;

import java.util.Scanner;


/**
 * Příkaz pro konverzaci s postavami.
 * Zobrazuje dialogy a zpracovává hádanky.
 *
 * @author Patrik Říha
 */
public class Talk implements Command {
    private Player player;
    private DataLoader world;
    private Scanner scanner;

    /**
     * Konstruktor pro příkaz rozhovoru.
     * @param player reference na hráče (pro přístup k lokaci a inventáři při odměně)
     * @param world  reference na herní svět (pro vyhledání postav, questů a předmětů)
     */
    public Talk(Player player, DataLoader world) {
        this.player = player;
        this.world = world;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Zpracuje uživatelský požadavek na rozhovor s konkrétní postavou.
     * Metoda ověřuje přítomnost postavy v místnosti, kontroluje stav přiděleného
     * úkolu a v případě hádanky spustí interaktivní režim pro zadání odpovědi.
     * @param command textový řetězec zadaný hráčem (např. "talk forest_fairy")
     * @return textový výstup dialogu, výsledek hádanky nebo chybové hlášení
     */
    @Override
    public String execute(String command) {
        String[] parts = command.split("\\s+");

        if (parts.length < 2) {
            return "Error: You have to specify a character id";
        }

        String npcId = parts[1].trim();
        Location currentLocation = player.getLocation();
        StringBuilder dialogue = new StringBuilder();

        try {
            Character npc = world.findCharacter(npcId);
            if (!npc.getLocation().equals(currentLocation.getId())) {
                return "Error: " + npc.getName() + " is not here.";
            }

            if (npc.getQuest() != null && !npc.getQuest().isEmpty()) {
                Quest quest = world.findQuest(npc.getQuest());

                if (quest.isCompleted()) {
                    return npc.getName() + " nods at you gratefully.\n" +
                            "\"Thank you for your help, traveler.\"";
                }

                if (quest.getRiddleAnswer() != null && !quest.getRiddleAnswer().isEmpty()) {
                    dialogue.append("\n").append(npc.getName()).append(" says:\n");
                    dialogue.append("\n").append(npc.getDialogue()).append("\n");

                    System.out.print(npc.getDialogue() +"\nYour answer: ");
                    String answer = scanner.nextLine().trim().toLowerCase();

                    if (answer.equals(quest.getRiddleAnswer().toLowerCase())) {
                        quest.setCompleted(true);
                        player.addToInventory(quest.getReward());
                        dialogue.append("\n").append(npc.getQuestCompleteDialogue()).append("\n");
                        dialogue.append("\nYou received: ").append(world.findItem(quest.getReward()).getName());
                        return dialogue.toString();
                    } else {
                        return "\n" + npc.getName() + " shakes their head.\n" +
                                "\"That is not the answer. Think more carefully.\"";
                    }
                }
                dialogue.append("\n").append(npc.getName()).append(" says:\n");
                dialogue.append("\"").append(npc.getDialogue()).append("\"");
            } else {
                dialogue.append("\n").append(npc.getName()).append(" says:\n");
                dialogue.append("\"").append(npc.getDialogue()).append("\"");
            }

            return dialogue.toString();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
