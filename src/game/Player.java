package game;

import java.util.ArrayList;
/**
 * Reprezentuje uživatele ve hře.
 * Spravuje aktuální lokaci hráče, inventář, předměty na oltáři a stav hry.
 *
 * @author Patrik Říha
 */
public class Player {
    private static final int maxInventory = 10;
    private Location location;
    private ArrayList<String> inventory;
    private ArrayList<String> altarItems;
    private boolean gameWon;

    /**
     * Vytvoří nového hráče s magickou hůlkou v jeho inventáři.
     */
    public Player() {
        this.inventory = new ArrayList<>();
        this.altarItems = new ArrayList<>();
        this.gameWon = false;
        this.inventory.add("magic_wand");
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Aktualizuje lokaci hráče (alias pro setLocation).
     * @param location nová lokace hráče
     */
    public void updateLocation(Location location) {
        this.location = location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    /**
     * Přidá předmět do inventáře.
     * @param itemId ID předmětu k přidání
     */
    public void addToInventory(String itemId) {
        if (inventory.size() >= maxInventory) {
            throw new IllegalStateException("Inventory is full! Maximum capacity: " + maxInventory + " items.");
        }
        inventory.add(itemId);
    }

    /**
     * Odebere předmět z inventáře.
     * @param itemId ID předmětu k odebrání
     */
    public void removeFromInventory(String itemId) {
        inventory.remove(itemId);
    }

    /**
     * Zjišťuje, zda je inventář plný.
     * @return true pokud je inventář plný, jinak false
     */
    public boolean isInventoryFull() {
        return inventory.size() >= maxInventory;
    }

    /**
     * Zjišťuje kolik předmětů má hráč v inventáři.
     * @return počet předmětů v inventáři
     */
    public int getInventorySize() {
        return inventory.size();
    }

    /**
     * Zjišťuje kolik lze dát předmětů do inventáře.
     * @return maximální počet předmětů v inventáři
     */
    public int getMaxInventorySize() {
        return maxInventory;
    }

    /**
     * Zjišťuje kolik je ještě volného místa v inventáři.
     * @return počet volných slotů v inventáři
     */
    public int getRemainingInventorySpace() {
        return maxInventory - inventory.size();
    }

    /**
     * Zjišťuje itemy které jsou dány na oltáři.
     * @return seznam ID předmětů na oltáři
     */
    public ArrayList<String> getAltarItems() {
        return altarItems;
    }

    /**
     * Přidává předmět na oltář.
     * @param itemId ID předmětu který uživatel chce umístit na oltář
     */
    public void addToAltarItems(String itemId) {
        altarItems.add(itemId);
    }

    /**
     * Zjišťuje, zda hráč umístil všechny požadované předměty na oltář.
     * @param required seznam požadovaných předmětů (jejich ID)
     * @return true pokud jsou všechny požadované předměty na oltáři, jinak false
     */
    public boolean hasAllAltarItems(ArrayList<String> required) {
        return altarItems.containsAll(required) && altarItems.size() == required.size();
    }

    /**
     * Zjišťuje, zda hráč vyhrál hru.
     * @return true pokud hráč vyhrál, jinak false
     */
    public boolean isGameWon() {
        return gameWon;
    }
    /**
     * Nastaví stav hry na dokončenou.
     * @param gameWon true pokud hráč vyhrál, jinak false
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}