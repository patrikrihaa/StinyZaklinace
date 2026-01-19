import game.DataLoader;

public class Main {
    public static void main(String[] args) {
        DataLoader data = DataLoader.loadGame("/data.json");

        System.out.println("Number of Locations: " + data.Locations.size());
        System.out.println(data.findLocation("l_windmill").getName() + ": " + data.findLocation("l_windmill").getNextLocations());
    }
}
