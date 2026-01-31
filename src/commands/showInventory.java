package commands;

import game.Player;

public class showInventory implements Command {

    private Player p;

    public showInventory(Player p) {
        this.p = p;
    }

    @Override
    public String execute(String command) {
        StringBuilder inventory = new StringBuilder();

        if (p == null || p.getInventory().isEmpty()) {
            return "Your inventory is empty.";
        }

        inventory.append("Your Inventory:\n ");
        for (String itemId : p.getInventory()) {
            inventory.append("- ").append(itemId).append("\n");
        }
        return inventory.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}