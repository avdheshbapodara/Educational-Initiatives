class CommandCenter {
    private static CommandCenter instance;

    private CommandCenter() {}

    public static CommandCenter getInstance() {
        if (instance == null) {
            instance = new CommandCenter();
        }
        return instance;
    }

    public void giveCommand(String command) {
        System.out.println("Command Center: " + command);
    }
}

// Main to test
public class Singleton {
    public static void main(String[] args) {
        CommandCenter command1 = CommandCenter.getInstance();
        command1.giveCommand("Initiate protocol A!");

        CommandCenter command2 = CommandCenter.getInstance();
        command2.giveCommand("Deploy team to sector 7!");

        // Both command1 and command2 should be the same instance
        System.out.println(command1 == command2);  // true
    }
}
