import ben.task.Task;
import ben.task.Todo;

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
     * Adds a member to the array tasks and increments tasksLength
     *
     * @param task task to add to the array tasks
     */
    private static void addTask(Task task) {
        tasks[tasksLength] = task;
        tasksLength++;
    }

    /**
     * Prints all members of the array tasks
     */
    private static void printTasks() {
        for (int index = 0; index < tasksLength; index++) {
            int currentIndex = index + 1;
            System.out.println(currentIndex + "." + tasks[index]);
        }
    }

    /**
     * Print a confirmation message for adding the task to
     * the array tasks
     *
     * @param task the Task object that has been added
     */
    private static void printTaskAdditionMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasksLength + " tasks in the list.");
    }

    /**
     * Set isDone attribute of element of type task
     * with the index number of indexNumber to true
     * and prints confirmation message
     *
     * @param indexNumber the index number of the element of type task
     *                    to set isDone attribute
     */
    private static void markAndPrintTaskDone(int indexNumber) {
        // Obtain index
        int index = indexNumber - 1;

        // Set the element as done
        tasks[index].markAsDone();

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
    private static void markAndPrintTaskUndone(int indexNumber) {
        // Obtain index
        int index = indexNumber - 1;

        // Set the element as not done
        tasks[index].markAsUndone();

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

        // Greet dialogue
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
            int commandParametersLength = commandParameters.length;

            String command = commandParameters[0];

            printLine();

            if (command.equals("bye")) {
                // Exit from loop
                break;
            } else if (command.equals("list")) {
                // List all elements
                printTasks();
            } else if (command.equals("mark")) {
                // Obtain indexNumber and mark done
                int indexNumber = Integer.parseInt(commandParameters[1]);
                markAndPrintTaskDone(indexNumber);
            } else if (command.equals("unmark")) {
                // Obtain indexNumber and mark not done
                int indexNumber = Integer.parseInt(commandParameters[1]);
                markAndPrintTaskUndone(indexNumber);
            } else if (command.equals("todo")) {
                // Obtain the description of the Todo task
                StringBuilder todoDescriptionBuilder = new StringBuilder(commandParameters[1]);
                for (int i = 2; i < commandParametersLength; i++) {
                    todoDescriptionBuilder.append(" ");
                    todoDescriptionBuilder.append(commandParameters[i]);
                }
                String todoDescription = todoDescriptionBuilder.toString();

                // Create Todo task
                Task todoTask = new Todo(todoDescription);

                // Add to array tasks
                addTask(todoTask);

                // Print confirmation message
                printTaskAdditionMessage(todoTask);
            } else {
                Task newTask = new Task(command);

                // Add to array tasks
                addTask(newTask);
                System.out.println("added: " + input);
            }

            printLine();
        }

        // Exit dialogue
        System.out.println("Bye. Hope to see you again soon!");

        printLine();
    }
}
