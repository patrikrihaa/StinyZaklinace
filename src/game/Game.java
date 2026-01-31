package game;

import commands.*;

import java.util.HashMap;

public class Game {

    private DataLoader world;
    private Player player;
    private HashMap<String, Command> commands;

    public void inicialization(){
        commands = new HashMap<>();
        world = DataLoader.loadGame("/data.json");
        player = new Player();

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
        commands.put("quit", new Quit());
        commands.put("exit", new Quit());
    }

    public void start(){
        inicialization();
        System.out.println("Game started\n");
        System.out.println(world.Characters.getFirst());
        System.out.println(world.Locations.getFirst());
        System.out.println(world.Items.getFirst());
        //TODO přidat sem herní smyčku
    }

}