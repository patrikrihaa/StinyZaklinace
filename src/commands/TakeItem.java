package commands;

import game.DataLoader;
import game.Item;
import game.Location;
import game.Player;

public class TakeItem implements Command {

    private Player p;
    private DataLoader world;

    public TakeItem(Player p, DataLoader world) {
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

        if (currentLocation.getItems() == null || !currentLocation.getItems().contains(itemId)) {
            return "You can't take this item: " + itemId + ". This item isn't available to pick up.";
        }

        try {
            Item item = world.findItem(itemId);
            p.addToInventory(itemId);
            currentLocation.getItems().remove(itemId);

            return "You have picked up: " + item.getName();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
