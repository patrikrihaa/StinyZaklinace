package game;

import commands.*;

import java.util.HashMap;
import java.util.Scanner;
/**
 * Hlavní herní kontroler.
 * Spravuje herní smyčku, příkazy a inicializaci hry.
 *
 * @author Patrik Říha
 */
public class Game {
    private DataLoader world;
    private Player player;
    private HashMap<String, Command> commands;
    private Scanner scanner;
    private boolean running;

    /**
     * Inicializace hry - načte data, vytvoří hráče, nastavý výchozí lokaci a zaregistruje příkazy.
     */
    public void initialization(){
        commands = new HashMap<>();
        world = DataLoader.loadGame("/data.json");
        player = new Player();
        scanner = new Scanner(System.in);
        running = true;

        player.updateLocation(world.findLocation("rotting_marshlands"));

        commands.put("move", new Movement(player, world));
        commands.put("go", new Movement(player, world));
        commands.put("help", new Help());
        commands.put("look", new LookAround(player));
        commands.put("inventory", new showInventory(player));
        commands.put("inv", new showInventory(player));
        commands.put("take", new TakeItem(player, world));
        commands.put("get", new TakeItem(player, world));
        commands.put("drop", new DropItem(player, world));
        commands.put("talk", new Talk(player, world));
        commands.put("use", new Use(player, world));
        commands.put("examine", new Examine(player, world));
        commands.put("hint", new Hint(player, world));
        commands.put("quit", new Quit());
        commands.put("exit", new Quit());
    }
    /**
     * Spustění hry - zobrazí úvod a spustí herní smyčku.
     */
    public void start(){
        initialization();
        displayIntro();
        gameLoop();
    }

    /**
     * Intro - vypíše krátký úvod do hry
     */
    private void displayIntro() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║           SHADOWS OF THE WITCHER                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        System.out.println("You awaken with a throbbing headache. The world around you");
        System.out.println("is blurred, your body heavy, and your mind empty. You try to");
        System.out.println("remember your name, your past, where you came from - but");
        System.out.println("there is only silence in your head. The concussion has taken");
        System.out.println("all your memories.\n");
        System.out.println("The entire world is cursed. No one knows exactly where it");
        System.out.println("came from, only that it changed everything it touched. People");
        System.out.println("exposed to it for too long transformed into monsters.\n");
        System.out.println("Your goal: Break the curse and find your way home.\n");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        System.out.println("Type 'help' to see available commands.\n");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        System.out.println(player.getLocation());
        System.out.println();
    }

    /**
     * Herní smyčka - načítá textový vstup od uživatele, vykonává zadané příkazy
     * a rozhoduje, zda uživatel dohrál hru.
     */
    private void gameLoop() {
        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+");
            String commandName = parts[0];

            Command cmd = commands.get(commandName);

            if (cmd != null) {
                String result = cmd.execute(input);
                System.out.println(result);

                if (player.isGameWon()) {
                    running = false;
                    System.out.println("\nThank you for playing Shadows of the Witcher!");
                    break;
                }

                if (cmd.exit()) {
                    running = false;
                    System.out.println("\nThank you for playing! Goodbye.");
                }
            } else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
        scanner.close();
    }
}