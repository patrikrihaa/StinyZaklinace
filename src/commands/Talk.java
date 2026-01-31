package commands;

import game.Character;
import game.DataLoader;
import game.Location;
import game.Player;

public class Talk implements Command {

    private Player p;
    private DataLoader world;

    public Talk(Player p, DataLoader world) {
        this.p = p;
        this.world = world;
    }

    @Override
    public String execute(String command) {
        String[] parts = command.split(" ");
        String npcId = parts[1];
        Location currentLocation = p.getLocation();
        StringBuilder dialogue = new StringBuilder();

        if (parts.length < 2) {
            return "Error: You have to specify a NPC id";
        }

        try {
            Character npc = world.findCharacter(npcId);
            if (!npc.getLocation().equals(currentLocation.getId())) {
                return "Error: " + npc.getName() + " is not here.";
            }

            dialogue.append("\n").append(npc.getName()).append(" says:\n");
            dialogue.append("\"").append(npc.getDialogue()).append("\"");

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
