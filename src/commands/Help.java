package commands;

public class Help implements Command {

    @Override
    public String execute(String command) {
        StringBuilder help = new StringBuilder();

        help.append("\n\nAvailable commands: \n");
        help.append("move <location_id>  - Move to a neighboring location\n");
        help.append("go <location_id>    - Alternative to move\n");
        help.append("look                - Look around current location\n");
        help.append("inventory           - Show your inventory\n");
        help.append("take <item_id>      - Take an item from current location\n");
        help.append("drop <item_id>      - Drop an item from inventory\n");
        help.append("talk <npc_id>       - Talk to an NPC\n");
        help.append("help                - Show this help message\n");
        help.append("quit                - Quit the game\n\n");
        return help.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}