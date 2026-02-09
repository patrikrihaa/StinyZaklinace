package commands;

import game.Location;
import game.Player;

/**
 * Příkaz pro rozhlédnutí se po aktuální lokaci.
 * Zobrazuje popis lokace, předměty a postavy.
 *
 * @author Patrik Říha
 */
public class LookAround implements Command {
    private Player player;

    /**
     * Konstruktor pro rozhlédnutí v dané lokaci.
     * @param player reference na hráče (pro zjištění jeho aktuální lokace a jejího obsahu)
     */
    public LookAround(Player player) {
        this.player = player;
    }

    /**
     * Zpracuje uživatelský požadavek a sestaví textový popis aktuálního okolí.
     * @param command textový řetězec zadaný hráčem (např. "look" nebo "rozhledni se")
     * @return text obsahující popis lokace a seznam předmětů
     */
    @Override
    public String execute(String command) {
        Location currentLocation = player.getLocation();
        StringBuilder description = new StringBuilder();

        description.append("\n").append(currentLocation.getDescription()).append("\n");

        if (currentLocation.getItems() != null && !currentLocation.getItems().isEmpty()) {
            description.append("\nYou see the following items here:\n");
            for (String item : currentLocation.getItems()) {
                description.append("  - ").append(item).append("\n");
            }
            description.append("You can pick up items using: take <item_id>\n");
        } else {
            description.append("\nThere are no items here.\n");
        }

        return description.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}