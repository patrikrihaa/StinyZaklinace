package commands;

import game.DataLoader;
import game.Item;
import game.Location;
import game.Player;

public class DropItem implements Command {

    private Player p;
    private DataLoader world;

    public DropItem(Player p, DataLoader world) {
        this.p = p;
        this.world = world;
    }

    @Override
    public String execute(String command) {
        String[] parts = command.split(" ");
        String itemId = parts[1];
        Location currentLocation = p.getLocation();

        if (parts.length < 2) {
            return "You have to specify an item id";
        }

        if (p.getInventory() == null || !p.getInventory().contains(itemId)) {
            return "You can't drop this item: " + itemId + ". This item isn't currently in your inventory.";
        }

        try {
            Item item = world.findItem(itemId);
            p.removeFromInventory(itemId);

            if (currentLocation.getItems() != null) {
                currentLocation.getItems().add(itemId);
            }

            return "You have dropped: " + item.getName();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
