package commands;

public class Quit implements Command {

    @Override
    public String execute(String command) {
        return "You have successfully ended the game.";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
