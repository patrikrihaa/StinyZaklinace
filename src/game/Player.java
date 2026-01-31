package game;

import java.util.ArrayList;

public class Player {

    private Location location;
    private ArrayList<String> inventory;

    public Player() {
        this.inventory = new ArrayList<>();
    }

    public Location getLocation() {
        return location;
    }

    public void updateLocation(Location location) {
        this.location = location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void addToInventory(String itemId) {
        inventory.add(itemId);
    }

    public void removeFromInventory(String itemId) {
        inventory.remove(itemId);
    }
}