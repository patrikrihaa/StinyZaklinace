package commands;

import game.*;

/**
 * Příkaz pro zobrazení inventáře hráče.
 * Ukazuje všechny předměty, které hráč vlastní.
 *
 * @author Patrik Říha
 */
public class showInventory implements Command {
    private Player player;

    /**
     * Konstruktor pro zobrazení inventáře.
     * @param player reference na hráče (pro přístup k jeho uloženým předmětům)
     */
    public showInventory(Player player) {
        this.player = player;
    }

    /**
     * Zpracuje uživatelský požadavek a sestaví textový výpis aktuálního inventáře.
     * @param command textový řetězec zadaný hráčem (např. "inventory")
     * @return seznam předmětů, případně zpráva, že je inventář prázdný
     */
    @Override
    public String execute(String command) {
        StringBuilder inventory = new StringBuilder();

        if (player == null || player.getInventory().isEmpty()) {
            return "Your inventory is empty.";
        }
        inventory.append("=== Your Inventory ("+ player.getInventorySize() + "/" + player.getMaxInventorySize()+ ") ===\n");
        for (String itemId : player.getInventory()) {
            inventory.append("- ").append(itemId).append("\n");
        }
        return inventory.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}