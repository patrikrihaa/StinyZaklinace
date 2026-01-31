package commands;

public interface Command {

    String execute(String command);
    boolean exit();


}
