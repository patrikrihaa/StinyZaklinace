package commands;

import game.DataLoader;
import game.Location;
import game.Player;

/**
 * Příkaz pro pohyb hráče mezi lokacemi.
 * Kontroluje dostupnost cílové lokace a zámky.
 *
 * @author Patrik Říha
 */
public class Movement implements Command {
    private Player player;
    private DataLoader world;

    /**
     * Konstruktor pro pohyb ve hře.
     * @param player reference na hráče (pro zjištění a aktualizaci jeho lokace, ověření inventáře)
     * @param world  reference na herní svět (pro přístup k datům lokací, předmětů a stavu úkolů)
     */
    public Movement(Player player, DataLoader world) {
        this.player = player;
        this.world = world;
    }

    /**
     * Zpracuje uživatelský požadavek na přesun do jiné lokace.
     * Vyhodnocuje, zda je zadaný cíl platný, volně přístupný , zamčený na konkrétní předmět,
     * nebo blokovaný příběhovou bariérou (např. Kruh druidů vyžaduje 6 splněných úkolů).
     * @param command celý vstup od hráče (očekává se formát např. "go <lokace_id>")
     * @return zpráva o úspěšném přesunu (včetně popisu nové lokace), nebo důvod, proč tam nelze jít
     */
    public String execute(String command) {
        String[] parts = command.split("\\s+");

        if (parts.length < 2) {
            return "You have to specify a location id";
        }

        String locationId = parts[1].trim();
        Location currentLocation = player.getLocation();

        if (!currentLocation.getNextLocations().contains(locationId)) {
            return "You can't move to location: " + locationId + ". This location isn't currently available.";
        }

        try {
            Location targetLocation = world.findLocation(locationId);

            if (targetLocation.isLocked()) {
                if (targetLocation.getId().equals("druids_star_circle")) {
                    int completedQuests = 0;
                    for (game.Quest quest : world.Quests) {
                        if (quest.isCompleted()) {
                            completedQuests++;
                        }
                    }

                    if (completedQuests >= 6) {
                        targetLocation.setLocked(false);
                    } else {
                        return "The path to the " + targetLocation.getName() + " is blocked by powerful magic.\n" +
                                "You must complete more quests before you can enter.\n" +
                                "Quests completed: " + completedQuests + " / 6";
                    }
                } else if (targetLocation.getUnlockItem() != null) {
                    if (player.getInventory().contains(targetLocation.getUnlockItem())) {
                        targetLocation.setLocked(false);
                        player.updateLocation(targetLocation);
                        return "You use the " + world.findItem(targetLocation.getUnlockItem()).getName() +
                                " to unlock the path.\n" +
                                "You have successfully moved to: " + targetLocation.getName() + "\n\n=== " + targetLocation.getName() + " ===\n" +
                                targetLocation.getDescription();
                    } else {
                        return "The path to " + targetLocation.getName() + " is locked.\n" +
                                "You need: " + world.findItem(targetLocation.getUnlockItem()).getName();
                    }
                } else {
                    return "The path to " + targetLocation.getName() + " is locked.";
                }
            }
            player.updateLocation(targetLocation);
            return "\n=== " + targetLocation.getName() + " ===\n" +
                    targetLocation.getDescription();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
