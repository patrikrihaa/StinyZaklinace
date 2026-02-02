package commands;

import game.DataLoader;
import game.Player;
import game.Quest;
import game.Character;

import java.util.ArrayList;

public class Hint implements Command {
    private Player player;
    private DataLoader world;

    public Hint(Player player, DataLoader world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public String execute(String command) {
        String locationId = player.getLocation().getId();

        if (locationId.equals("druids_star_circle")) {
            return getAltarHint();
        }

        ArrayList<Character> chars = world.getCharactersInLocation(locationId);
        for (Character character : chars) {
            if (character.getQuest() != null && !character.getQuest().isEmpty()) {
                try {
                    Quest quest = world.findQuest(character.getQuest());
                    if (!quest.isCompleted()) {
                        return getQuestHint(quest, character);
                    }
                } catch (IllegalArgumentException e) {
                }
            }
        }

        return getLocationHint(locationId);
    }

    private String getAltarHint() {
        int placed = player.getAltarItems().size();
        int total = world.AltarRequirements.size();

        if (placed == 0) {
            return "You need to gather six ritual artifacts and place them on the altar.\n" +
                    "Complete quests throughout the land to obtain these artifacts.";
        } else if (placed < total) {
            return "You've placed " + placed + " of " + total + " artifacts.\n" +
                    "Continue exploring and completing quests to find the remaining artifacts.";
        } else {
            return "All artifacts are in place. The ritual should activate automatically.";
        }
    }

    private String getQuestHint(Quest quest, Character character) {
        if (quest.isCombat()) {
            if (player.getInventory().contains("magic_wand")) {
                return "You sense a hostile presence. Use your wand to defend yourself!";
            } else {
                return "You'll need a weapon to face this threat. Have you found your wand?";
            }
        }

        if (quest.getRiddleAnswer() != null && !quest.getRiddleAnswer().isEmpty()) {
            return "Listen carefully to " + character.getName() + "'s riddle.\n" +
                    "Think about the clues. When you have the answer, talk to them again.";
        }

        if (quest.getRequiredItem() != null && !quest.getRequiredItem().isEmpty()) {
            if (player.getInventory().contains(quest.getRequiredItem())) {
                return "You have what " + character.getName() + " needs!\n" +
                        "Use the item to complete the quest: use " + quest.getRequiredItem();
            } else {
                return character.getName() + " needs: " + quest.getRequiredItem() + "\n" +
                        "Explore other locations to find it.";
            }
        }

        return "Talk to " + character.getName() + " to learn more about what they need.";
    }

    private String getLocationHint(String locationId) {
        switch (locationId) {
            case "rotting_marshlands":
                return "This is where you began. Look around carefully - you might find useful items.\n" +
                        "Don't forget to take everything you can carry.";

            case "forsaken_town":
                return "This town connects to several important locations.\n" +
                        "Make sure to talk to anyone you meet here.";

            case "windmill":
                return "This location might be locked. You'll need the right key to enter.\n" +
                        "Complete quests in the town to obtain keys.";

            case "caverns_of_oblivion":
                return "It's very dark here. You'll need a light source to navigate safely.\n" +
                        "Have you visited the Sunny Glade?";

            case "sunny_glade":
                return "This peaceful place is home to a magical guardian.\n" +
                        "She might reward clever travelers.";

            default:
                return "Explore thoroughly and talk to everyone you meet.\n" +
                        "Most quests require specific items or answers to riddles.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
