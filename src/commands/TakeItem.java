package commands;

import game.*;

/**
 * Příkaz pro sebrání předmětu z lokace.
 * Přidá předmět do inventáře hráče a odebere ho z lokace.
 *
 * @author Patrik Říha
 */
public class TakeItem implements Command {
    private Player player;
    private DataLoader world;

    /**
     * Konstruktor pro příkaz sebrání předmětu.
     * @param player reference na hráče (pro manipulaci s inventářem a zjištění aktuální lokace)
     * @param world  reference na herní svět (pro vyhledání dat o konkrétním předmětu)
     */
    public TakeItem(Player player, DataLoader world) {
        this.player = player;
        this.world = world;
    }

    /**
     * Zpracuje uživatelský požadavek a pokusí se sebrat specifikovaný předmět.
     * Zkontroluje, zda byl zadán název, zda předmět leží v místnosti a zda má
     * hráč v inventáři ještě volné místo. Pokud vše sedí, předmět se přesune.
     * @param command textový řetězec zadaný hráčem (např. "take glasses")
     * @return zpráva o úspěšném získání předmětu, případně chybové hlášení
     */
    @Override
    public String execute(String command) {
        String[] parts = command.split("\\s+");
        Location currentLocation = player.getLocation();

        if (parts.length < 2) {
            return "You have to specify an item id";
        }
        String itemId = parts[1].trim();

        if (currentLocation.getItems() == null || !currentLocation.getItems().contains(itemId)) {
            return "You can't take this item: " + itemId + ". This item isn't available to pick up.";
        }

               if (player.isInventoryFull()) {
                   return "Your inventory is full! (Maximum: " + player.getMaxInventorySize() + " items)\n"
                           + "You need to drop something before you can pick up more items.\n"
                           + "Current inventory space: " + player.getInventorySize() + "/" + player.getMaxInventorySize();
               }


        try {
            Item item = world.findItem(itemId);
            player.addToInventory(itemId);
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
