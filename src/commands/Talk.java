package commands;

import game.Character;
import game.DataLoader;
import game.Location;
import game.Player;
import game.Quest;

import java.util.Scanner;

public class Talk implements Command {

    private Player p;
    private DataLoader world;
    private Scanner scanner;

    public Talk(Player p, DataLoader world) {
        this.p = p;
        this.world = world;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String execute(String command) {
        String[] parts = command.split(" ");

        if (parts.length < 2) {
            return "Error: You have to specify a character id";
        }

        String npcId = parts[1].trim();
        Location currentLocation = p.getLocation();
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
                        p.addToInventory(quest.getReward());
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
