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
        String locationId = parts[1];
        Location currentLocation = p.getLocation();

        if (parts.length < 2) {
            return "You have to specify a location id";

        } else if (!currentLocation.getNextLocations().contains(locationId)) {
            return "You can't move to location: " + locationId + ". This location isn't currently available.";
        }

        try {
            Location targetLocation = world.findLocation(locationId);
            p.updateLocation(targetLocation);
            return "You have successfully moved to: " + targetLocation.getName();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
