package commands;

import game.DataLoader;
import game.Item;
import game.Location;
import game.Player;

/**
 * Příkaz pro zahození předmětu z inventáře.
 * Odebere předmět z inventáře a přidá ho do aktuální lokace.
 *
 * @author Patrik Říha
 */
public class DropItem implements Command {
    private Player player;
    private DataLoader world;

    /**
     * Konstruktor pro zahození předmětu.
     * @param player reference na hráče (pro zjištění jeho inventáře a lokace)
     * @param world  reference na herní svět (pro přístup k úkolům a postavám)
     */
    public DropItem(Player player, DataLoader world) {
        this.player = player;
        this.world = world;
    }

    /**
     * Zpracuje uživatelský vstup a pokusí se zahodit specifikovaný předmět.
     *
     * @param command textový řetězec zadaný hráčem (např. "drop glasses")
     * @return zpráva pro hráče o výsledku akce, která se následně vypíše do konzole
     */
    @Override
    public String execute(String command) {
        String[] parts = command.split("\\s+");
        Location currentLocation = player.getLocation();

        if (parts.length < 2) {
            return "You have to specify an item id";
        }
        String itemId = parts[1];

        if (player.getInventory() == null || !player.getInventory().contains(itemId)) {
            return "You can't drop this item: " + itemId + ". This item isn't currently in your inventory.";
        }

        try {
            Item item = world.findItem(itemId);
            player.removeFromInventory(itemId);

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
