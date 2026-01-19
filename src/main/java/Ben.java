public class Ben {
    public static final String name = "Ben";

    // Utility function to print line
    public static void PrintLine() {
        final String line = "____________________________________________________________";
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        PrintLine();

        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        // Perform tasks

        PrintLine();

        // Exit dialogue
        System.out.println("Bye. Hope to see you again soon!");

        PrintLine();
    }
}
