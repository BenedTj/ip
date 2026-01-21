import ben.task.Task;

import java.util.Scanner;

public class Ben {
    private static final String NAME = "Ben";
    private static final String LINE = "____________________________________________________________";
    private static Task[] tasks = new Task[100];
    private static int tasksLength = 0;

    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Adds a member to the list tasks and increments tasksLength
     *
     * @param Element member to add to the list tasks
     */
    private static void addElement(Task Element) {
        tasks[tasksLength] = Element;
        tasksLength++;
    }

    /**
     * Prints all members of the list tasks
     */
    private static void printElements() {
        for (int index = 0; index < tasksLength; index++) {
            int currentIndex = index + 1;
            System.out.println(currentIndex + ". " + tasks[index]);
        }
    }

    /**
     * Set isDone attribute of element of type task
     * with the index number of indexNumber to true
     * and prints confirmation message
     *
     * @param indexNumber the index number of the element of type task
     *                    to set isDone attribute
     */
    private static void markAndPrintElementDone(int indexNumber) {
        // Obtain index
        int index = indexNumber - 1;

        // Set the element as done
        tasks[index].setIsDone(true);

        // Print message to confirm
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index].toString());
    }

    /**
     * Set isDone attribute of element of type task
     * with the index number of indexNumber to false
     * and prints confirmation message
     *
     * @param indexNumber the index number of the element of type task
     *                    to set isDone attribute
     */
    private static void markAndPrintElementUndone(int indexNumber) {
        // Obtain index
        int index = indexNumber - 1;

        // Set the element as not done
        tasks[index].setIsDone(false);

        // Print message to confirm
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  " + tasks[index].toString());
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

            // Split input into commands and parameters
            String[] commandParameters = input.split(" ");

            String command = commandParameters[0];

            printLine();

            if (command.equals("bye")) {
                // Exit from loop
                break;
            } else if (command.equals("list")) {
                // List all elements
                printElements();
            } else if (command.equals("mark")) {
                // Obtain indexNumber and mark done
                int indexNumber = Integer.parseInt(commandParameters[1]);
                markAndPrintElementDone(indexNumber);
            } else if (command.equals("unmark")) {
                // Obtain indexNumber and mark not done
                int indexNumber = Integer.parseInt(commandParameters[1]);
                markAndPrintElementUndone(indexNumber);
            } else {
                Task newTask = new Task(command);

                // Add to list
                addElement(newTask);
                System.out.println("added: " + input);
            }

            printLine();
        }

        // Exit dialogue
        System.out.println("Bye. Hope to see you again soon!");

        printLine();
    }
}
