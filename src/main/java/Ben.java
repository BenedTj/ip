import ben.exception.BenEmptyParameterValueException;
import ben.exception.BenException;
import ben.exception.BenIndexOutOfRangeException;
import ben.exception.BenInvalidCommandException;
import ben.exception.BenInvalidParameterException;
import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;
import ben.exception.BenMissingParameterException;
import ben.task.Deadline;
import ben.task.Event;
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
        System.out.println("Here are the tasks in your list:");
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
     * @throws BenMarkAlreadyDoneException If the task has already been marked as done
     */
    private static void markAndPrintTaskDone(int indexNumber) throws BenMarkAlreadyDoneException {
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
     * @throws BenMarkAlreadyNotDoneException If the task has already been marked as not done
     */
    private static void markAndPrintTaskUndone(int indexNumber) throws BenMarkAlreadyNotDoneException {
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

            try {
                if (command.equals("bye")) {
                    // Exit from loop
                    break;
                } else if (command.equals("list")) {
                    // List all elements
                    printTasks();
                } else if (command.equals("mark")) {
                    try {
                        // Obtain indexNumber
                        int indexNumber = Integer.parseInt(commandParameters[1]);

                        /* If indexNumber is outside of the length of commandParameters,
                           throw an exception
                         */
                        if (indexNumber > tasksLength) {
                            throw new BenIndexOutOfRangeException(indexNumber);
                        }

                        // Mark done
                        markAndPrintTaskDone(indexNumber);
                    } catch (NumberFormatException e) {
                        throw new BenInvalidParameterException(commandParameters[1]);
                    }
                } else if (command.equals("unmark")) {
                    try {
                        // Obtain indexNumber
                        int indexNumber = Integer.parseInt(commandParameters[1]);

                        /* If indexNumber is outside of the length of commandParameters,
                           throw an exception
                         */
                        if (indexNumber > tasksLength) {
                            throw new BenIndexOutOfRangeException(indexNumber);
                        }

                        // Mark not done
                        markAndPrintTaskUndone(indexNumber);
                    } catch (NumberFormatException e) {
                        throw new BenInvalidParameterException(commandParameters[1]);
                    }
                } else if (command.equals("todo")) {
                    // Throw an exception if there is no description
                    if (commandParametersLength <= 1) {
                        throw new BenEmptyParameterValueException("description", "todo");
                    }

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
                } else if (command.equals("deadline")) {
                    // Throw an exception if there is no description
                    if (commandParametersLength <= 1) {
                        throw new BenEmptyParameterValueException("description", "deadline");
                    }

                    Integer byIndex = null;

                    // Obtain the description of the Deadline task
                    StringBuilder deadlineDescriptionBuilder = new StringBuilder(commandParameters[1]);
                    for (int i = 2; i < commandParametersLength; i++) {
                        // Stop once "/by" is encountered
                        if (commandParameters[i].equals("/by")) {
                            byIndex = i;
                            break;
                        }

                        deadlineDescriptionBuilder.append(" ");
                        deadlineDescriptionBuilder.append(commandParameters[i]);
                    }
                    String deadlineDescription = deadlineDescriptionBuilder.toString();

                    // Throw an exception if there is no "/by"
                    if (byIndex == null) {
                        throw new BenMissingParameterException("/by");
                    }

                    // Throw an exception if there is no value for "/by"
                    if (byIndex + 1 >= commandParametersLength) {
                        throw new BenEmptyParameterValueException("/by", "deadline");
                    }

                    // Obtain the deadline of the Deadline task
                    StringBuilder deadlineByBuilder = new StringBuilder(commandParameters[byIndex + 1]);
                    for (int i = byIndex + 2; i < commandParametersLength; i++) {
                        deadlineByBuilder.append(" ");
                        deadlineByBuilder.append(commandParameters[i]);
                    }
                    String deadlineBy = deadlineByBuilder.toString();

                    // Create Deadline task
                    Task deadlineTask = new Deadline(deadlineDescription, deadlineBy);

                    // Add to array tasks
                    addTask(deadlineTask);

                    // Print confirmation message
                    printTaskAdditionMessage(deadlineTask);
                } else if (command.equals("event")) {
                    // Throw an exception if there is no description
                    if (commandParametersLength <= 1) {
                        throw new BenEmptyParameterValueException("description", "event");
                    }

                    Integer fromIndex = null;
                    Integer toIndex = null;

                    // Obtain the description of the Event task
                    StringBuilder eventDescriptionBuilder = new StringBuilder(commandParameters[1]);
                    for (int i = 2; i < commandParametersLength; i++) {
                        // Stop once "/from" is encountered
                        if (commandParameters[i].equals("/from")) {
                            fromIndex = i;
                            break;
                        }

                        eventDescriptionBuilder.append(" ");
                        eventDescriptionBuilder.append(commandParameters[i]);
                    }
                    String eventDescription = eventDescriptionBuilder.toString();

                    // Throw an exception if there is no "/from"
                    if (fromIndex == null) {
                        throw new BenMissingParameterException("/from");
                    }

                    // Throw an exception if there is no value for "/from"
                    if (fromIndex + 1 >= commandParametersLength
                            || commandParameters[fromIndex + 1].equals("/to")) {
                        throw new BenEmptyParameterValueException("/from", "event");
                    }

                    // Obtain the to date/time of the Event task
                    StringBuilder eventFromBuilder = new StringBuilder(commandParameters[fromIndex + 1]);
                    for (int i = fromIndex + 2; i < commandParametersLength; i++) {
                        // Stop once "/end" is encountered
                        if (commandParameters[i].equals("/to")) {
                            toIndex = i;
                            break;
                        }

                        eventFromBuilder.append(" ");
                        eventFromBuilder.append(commandParameters[i]);
                    }
                    String eventFrom = eventFromBuilder.toString();

                    // Throw an exception if there is no "/to"
                    if (toIndex == null) {
                        throw new BenMissingParameterException("/to");
                    }

                    // Throw an exception if there is no value for "/to"
                    if (toIndex + 1 >= commandParametersLength) {
                        throw new BenEmptyParameterValueException("/to", "event");
                    }

                    // Obtain the end date/time of the Event task
                    StringBuilder eventToBuilder = new StringBuilder(commandParameters[toIndex + 1]);
                    for (int i = toIndex + 2; i < commandParametersLength; i++) {
                        eventToBuilder.append(" ");
                        eventToBuilder.append(commandParameters[i]);
                    }
                    String eventTo = eventToBuilder.toString();

                    // Create Event task
                    Task eventTask = new Event(eventDescription, eventFrom, eventTo);

                    // Add to array tasks
                    addTask(eventTask);

                    // Print confirmation message
                    printTaskAdditionMessage(eventTask);
                } else {
                    // If it does not match any command, throw an exception
                    throw new BenInvalidCommandException();
                }
            } catch (BenException e) {
                System.out.println(e.toString());
            }

            printLine();
        }

        // Exit dialogue
        System.out.println("Bye. Hope to see you again soon!");

        printLine();
    }
}
