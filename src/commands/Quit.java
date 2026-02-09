package commands;

/**
 * Příkaz pro ukončení hry.
 * Nastaví příznak pro ukončení herní smyčky.
 *
 * @author Patrik Říha
 */
public class Quit implements Command {

    /**
     * Zpracuje požadavek na ukončení hry a připraví zprávu na rozloučenou.
     * @param command textový řetězec zadaný hráčem (např. "quit")
     * @return textová zpráva potvrzující ukončení hry
     */
    @Override
    public String execute(String command) {
        return "You have successfully ended the game.";
    }

    /**
     * Po zpracování příkazu se hra automaciky ukončuje.
     * @return true pro ukončení hry
     */
    @Override
    public boolean exit() {
        return true;
    }
}
