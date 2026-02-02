package commands;

import game.DataLoader;
import game.Location;
import game.Player;

public class Movement implements Command {
    private Player p;
    private DataLoader world;

    public Movement(Player p, DataLoader world) {
        this.p = p;
        this.world = world;
    }

    public String execute(String command) {
        String[] parts = command.split(" ");

        if (parts.length < 2) {
            return "You have to specify a location id";
        }

        String locationId = parts[1].trim();
        Location currentLocation = p.getLocation();

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
                    if (p.getInventory().contains(targetLocation.getUnlockItem())) {
                        targetLocation.setLocked(false);
                        return "You use the " + world.findItem(targetLocation.getUnlockItem()).getName() +
                                " to unlock the path.\n" +
                                "You have successfully moved to: " + targetLocation.getName() + "\n\n" +
                                targetLocation.getDescription();
                    } else {
                        return "The path to " + targetLocation.getName() + " is locked.\n" +
                                "You need: " + world.findItem(targetLocation.getUnlockItem()).getName();
                    }
                } else {
                    return "The path to " + targetLocation.getName() + " is locked.";
                }
            }

            p.updateLocation(targetLocation);
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
