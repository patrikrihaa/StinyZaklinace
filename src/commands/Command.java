package commands;

/**
 * Rozhraní pro všechny herní příkazy.
 * Všechny příkazy implementují toto rozhraní a poskytují metodu execute.
 *
 * @author Patrik Říha
 */
public interface Command {

    String execute(String command);

    /**
     * Zjišťuje, zda příkaz ukončuje hru.
     * @return true pokud příkaz ukončuje hru, jinak false
     */
    boolean exit();


}
