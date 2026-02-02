package commands;

import game.DataLoader;
import game.Item;
import game.Location;
import game.Player;
import game.Character;

import java.util.ArrayList;

public class Examine implements Command {
    private Player player;
    private DataLoader world;

    public Examine(Player player, DataLoader world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public String execute(String command) {
        String[] parts = command.split(" ");

        if (parts.length < 2) {
            return examineLocation();
        }

        String target = parts[1].trim();

        if (target.equalsIgnoreCase("altar") && player.getLocation().getId().equals("druids_star_circle")) {
            return examineAltar();
        }

        try {
            Item item = world.findItem(target);
            return "You examine the " + item.getName() + ".\n" + item.getDescription();
        } catch (IllegalArgumentException e) {
            try {
                Character character = world.findCharacter(target);
                return character.getDescription();
            } catch (IllegalArgumentException e2) {
                return "You don't see anything like that here.";
            }
        }
    }

    private String examineLocation() {
        Location loc = player.getLocation();
        StringBuilder result = new StringBuilder();
        result.append("\n=== ").append(loc.getName()).append(" ===\n");
        result.append(loc.getDescription()).append("\n\n");

        if (loc.getItems() != null && !loc.getItems().isEmpty()) {
            result.append("Items here:\n");
            for (String itemId : loc.getItems()) {
                Item item = world.findItem(itemId);
                result.append("  - ").append(item.getName()).append("\n");
            }
            result.append("\n");
        }

        ArrayList<Character> chars = world.getCharactersInLocation(loc.getId());
        if (!chars.isEmpty()) {
            result.append("Characters here:\n");
            for (Character c : chars) {
                result.append("  - ").append(c.getName()).append("\n");
            }
            result.append("\n");
        }

        if (loc.getNextLocations() != null && !loc.getNextLocations().isEmpty()) {
            result.append("Available exits:\n");
            for (String nextId : loc.getNextLocations()) {
                try {
                    Location nextLoc = world.findLocation(nextId);
                    result.append("  - ").append(nextId).append(" (").append(nextLoc.getName()).append(")\n");
                } catch (IllegalArgumentException e) {
                    result.append("  - ").append(nextId).append("\n");
                }
            }
        }

        return result.toString();
    }

    private String examineAltar() {
        StringBuilder result = new StringBuilder();
        result.append("An ancient stone altar stands before you, carved with mystical symbols.\n");
        result.append("Six circular indentations await the ritual artifacts.\n\n");

        if (player.getAltarItems().isEmpty()) {
            result.append("No artifacts have been placed yet.\n");
        } else {
            result.append("Artifacts placed: ").append(player.getAltarItems().size())
                    .append(" of ").append(world.AltarRequirements.size()).append("\n");
            for (String itemId : player.getAltarItems()) {
                Item item = world.findItem(itemId);
                result.append("  ✓ ").append(item.getName()).append("\n");
            }
        }

        int remaining = world.AltarRequirements.size() - player.getAltarItems().size();
        if (remaining > 0) {
            result.append("\nYou still need ").append(remaining).append(" more artifacts.");
        }

        return result.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
