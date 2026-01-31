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

        if (currentLocation.getItems() != null) {
            description.append("\nCongratulations, you have found ").append(currentLocation.getItems()).append("!");
            description.append("\nYou can pick it up using command: take <item_id>");
        } else if (!currentLocation.getItems().isEmpty()) {
            description.append("\nCongratulations, you have found ").append(currentLocation.getItems()).append("!");
            description.append("\nYou can pick it up using command: take <item_id>");
        }
        return description.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}