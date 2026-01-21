import java.util.Scanner;

public class Ben {
    private static final String NAME = "Ben";
    private static final String LINE = "____________________________________________________________";
    private static String[] strings = new String[100];
    private static int stringsLength = 0;

    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Adds a member to the list strings and increments stringsLength
     *
     * @param Element member to add to the list strings
     */
    private static void addElement(String Element) {
        strings[stringsLength] = Element;
        stringsLength++;
    }

    /**
     * Prints all members of the list strings
     */
    private static void printElements() {
        for (int index = 0; index < stringsLength; index++) {
            int currentIndex = index + 1;
            System.out.println(currentIndex + ". " + strings[index]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();

        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");

        printLine();

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Perform task
        while (true) {
            String input = scanner.nextLine();
            printLine();
            if (input.equals("bye")) {
                // Exit from loop
                break;
            } else if (input.equals("list")) {
                // List all elements
                printElements();
            } else {
                // Add to list
                addElement(input);
                System.out.println("added: " + input);
            }

            printLine();
        }

        // Exit dialogue
        System.out.println("Bye. Hope to see you again soon!");

        printLine();
    }
}
