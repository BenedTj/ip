import java.util.*;

public class Ben {
    public static final String name = "Ben";
    private static String[] list = new String[100];
    private static int listLength = 0;

    // Utility function to print line
    public static void PrintLine() {
        final String line = "____________________________________________________________";
        System.out.println(line);
    }

    // Utility function to add members to the list
    private static void AddElement(String Element) {
        list[listLength] = Element;
        listLength++;
    }

    // Utility function to list all members of the list
    private static void PrintElements() {
        for (int index = 0; index < listLength; index++) {
            System.out.println(list[index]);
        }
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

        PrintLine();

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Perform task
        while (true) {
            String input = scanner.nextLine();
            PrintLine();
            if (input.equals("bye")) {
                // Exit from loop
                break;
            } else {
                // Echo user input
                System.out.println(input);
                PrintLine();
            }
        }

        // Exit dialogue
        System.out.println("Bye. Hope to see you again soon!");

        PrintLine();
    }
}
