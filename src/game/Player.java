package game;

import java.util.ArrayList;

public class Player {

    private Location location;
    private ArrayList<String> inventory;
    private ArrayList<String> altarItems;
    private boolean gameWon;

    public Player() {
        this.inventory = new ArrayList<>();
        this.altarItems = new ArrayList<>();
        this.gameWon = false;
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

    public ArrayList<String> getAltarItems() {
        return altarItems;
    }

    public void addToAltarItems(String itemId) {
        altarItems.add(itemId);
    }

    public boolean hasAllAltarItems(ArrayList<String> required) {
        return altarItems.containsAll(required) && altarItems.size() == required.size();
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}