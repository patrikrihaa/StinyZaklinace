package commands;

import game.DataLoader;
import game.Item;
import game.Player;
import game.Quest;
import game.Character;

public class Use implements Command {
    private Player player;
    private DataLoader world;

    public Use(Player player, DataLoader world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public String execute(String command) {
        String[] parts = command.split(" ");

        if (parts.length < 2) {
            return "You must specify an item to use. Example: use magic_wand";
        }

        String itemId = parts[1].trim();

        if (!player.getInventory().contains(itemId)) {
            return "You don't have that item in your inventory.";
        }

        if (player.getLocation().getId().equals("druids_star_circle")) {
            return handleAltarUse(itemId);
        }

        return handleItemUse(itemId);
    }

    private String handleAltarUse(String itemId) {
        if (world.AltarRequirements.contains(itemId)) {
            player.addToAltarItems(itemId);
            player.removeFromInventory(itemId);

            if (player.hasAllAltarItems(world.AltarRequirements)) {
                player.setGameWon(true);
                return "\nYou place the " + world.findItem(itemId).getName() + " on the altar.\n\n" +
                        "═══════════════════════════════════════════════════════════\n" +
                        "The six ritual artifacts glow with brilliant light!\n" +
                        "The altar erupts in a pillar of radiant energy!\n" +
                        "The curse shatters like glass, its dark hold broken forever!\n" +
                        "Humanity transforms back from their monstrous forms!\n" +
                        "A portal of light opens before you - the way home!\n\n" +
                        "CONGRATULATIONS! YOU HAVE WON THE GAME!\n" +
                        "═══════════════════════════════════════════════════════════\n";
            } else {
                int remaining = world.AltarRequirements.size() - player.getAltarItems().size();
                return "You place the " + world.findItem(itemId).getName() + " on the altar. " +
                        "It begins to glow with power. You need " + remaining + " more artifacts.";
            }
        }
        return "The altar doesn't react to this item. You need the six ritual artifacts.";
    }

    private String handleItemUse(String itemId) {
        String locationId = player.getLocation().getId();

        for (Character character : world.getCharactersInLocation(locationId)) {
            if (character.getQuest() != null && !character.getQuest().isEmpty()) {
                Quest quest = world.findQuest(character.getQuest());

                if (quest.isCombat() && itemId.equals("magic_wand")) {
                    if (!quest.isCompleted()) {
                        quest.setCompleted(true);
                        player.addToInventory(quest.getReward());
                        return "You raise your wand and channel your magical power!\n" +
                                "Arcanist Malrec counters with dark spells, but your magic is stronger!\n" +
                                "With a final burst of energy, you break through his defenses!\n\n" +
                                character.getQuestCompleteDialogue() + "\n\n" +
                                "You received: " + world.findItem(quest.getReward()).getName();
                    }
                    return "You've already defeated Arcanist Malrec.";
                }

                if (quest.getRequiredItem() != null && quest.getRequiredItem().equals(itemId)) {
                    if (!quest.isCompleted()) {
                        quest.setCompleted(true);
                        player.removeFromInventory(itemId);
                        player.addToInventory(quest.getReward());
                        return character.getQuestCompleteDialogue() + "\n\n" +
                                "You received: " + world.findItem(quest.getReward()).getName();
                    }
                    return "You've already completed this quest.";
                }
            }
        }

        try {
            Item item = world.findItem(itemId);
            return "You examine the " + item.getName() + ". " + item.getDescription();
        } catch (IllegalArgumentException e) {
            return "You can't use that here.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
