package commands;

import game.Location;
import game.Player;

public class LookAround implements Command {

    private Player p;

    public LookAround(Player p) {
        this.p = p;
    }

    @Override
    public String execute(String command) {
        Location currentLocation = p.getLocation();
        StringBuilder description = new StringBuilder();

        description.append("\n").append(currentLocation.getDescription()).append("\n");

        if (currentLocation.getItems() != null && !currentLocation.getItems().isEmpty()) {
            description.append("\nYou see the following items here:\n");
            for (String item : currentLocation.getItems()) {
                description.append("  - ").append(item).append("\n");
            }
            description.append("You can pick up items using: take <item_id>\n");
        } else {
            description.append("\nThere are no items here.\n");
        }

        return description.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}