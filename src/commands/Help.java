package commands;

public class Help implements Command {

    @Override
    public String execute(String command) {
        StringBuilder help = new StringBuilder();

        help.append("\n╔═══════════════════════════════════════════════════════════╗\n");
        help.append("║               SHADOWS OF THE WITCHER - HELP               ║\n");
        help.append("╚═══════════════════════════════════════════════════════════╝\n\n");
        help.append("MOVEMENT:\n");
        help.append("  go <location_id>        - Move to a neighboring location\n");
        help.append("  move <location_id>      - Alternative to 'go'\n\n");
        help.append("INVENTORY:\n");
        help.append("  inventory               - Show your inventory\n");
        help.append("  inv                     - Short form of inventory\n");
        help.append("  take <item_id>          - Take an item from current location\n");
        help.append("  get <item_id>           - Alternative to 'take'\n");
        help.append("  drop <item_id>          - Drop an item from inventory\n\n");
        help.append("INTERACTION:\n");
        help.append("  use <item_id>           - Use an item from your inventory\n");
        help.append("  talk <character_id>     - Talk to a character\n");
        help.append("  examine <target>        - Examine location, item, or character\n");
        help.append("  look                    - Look around current location\n\n");
        help.append("ASSISTANCE:\n");
        help.append("  hint                    - Get a hint for current situation\n");
        help.append("  help                    - Show this help message\n\n");
        help.append("GAME CONTROL:\n");
        help.append("  quit                    - Quit the game\n");
        help.append("  exit                    - Alternative to 'quit'\n\n");
        help.append("═══════════════════════════════════════════════════════════\n");
        help.append("TIP: Use 'examine' without a target to see everything in\n");
        help.append("     your current location, including available exits.\n");
        help.append("═══════════════════════════════════════════════════════════\n");
        return help.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}