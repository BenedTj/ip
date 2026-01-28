import ben.exception.*;
import ben.task.Deadline;
import ben.task.Event;
import ben.task.Task;
import ben.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ben {
    private static final String NAME = "Ben";
    private static final String LINE = "____________________________________________________________";
    private static final String FILE_PATH = "./data/ben/tasks.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Return the contents of the file with the filePath
     * and create the file if it does not exist
     *
     * @param filePath the path of the file to be accessed
     * @return the text content of the file
     * @throws FileNotFoundException If the file is not found
     *                               (never thrown because of the file
     *                               existence check)
     * @throws IOException If an input/output runtime exception occurs
     */
    private static String initializeRawDataFromPath(String filePath) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            return fileScanner.toString();
        } else {
            boolean status = file.createNewFile();
            return "";
        }
    }

    /**
     * Writes the content of the file found in filePath with
     * the string content
     *
     * @param filePath the path of the file
     * @param content the content to overwrite the file with
     * @throws IOException If a runtime input/output exception is thrown
     */
    private static void overwriteRawData(String filePath, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, false);
        fileWriter.write(content);
        fileWriter.close();
    }

    /**
     * Returns the raw string representation of the current tasks
     *
     * @return the string that contains the representations of the
     *         tasks within tasks
     */
    private static String getTasksRepresentation() {
        int tasksLength = tasks.size();

        if (tasks.isEmpty()) {
            return "";
        } else {
            // Write each task by ascending order in tasks
            StringBuilder representationBuilder = new StringBuilder(tasks.get(0).toRepresentation());

            for (int i = 1; i < tasksLength; i++) {
                // Write each task line by line
                representationBuilder.append(System.lineSeparator());
                representationBuilder.append(tasks.get(i).toRepresentation());
            }

            return representationBuilder.toString();
        }
    }

    /**
     * Saves tasks in the file found in the filePath
     *
     * @param filePath the path of the file
     * @throws IOException If an input/output exception occurs
     */
    private static void overwriteTasksFile(String filePath) throws IOException {
        String tasksRepresentation = getTasksRepresentation();
        overwriteRawData(filePath, tasksRepresentation);
    }

    private static void loadSavedTasks(String content)
            throws BenInvalidFileFormatException, IOException {
        // Clears content of tasks first
        tasks.clear();

        String[] lines = content.split(System.lineSeparator());
        int linesLength = lines.length;

        for (int i = 0; i < linesLength; i++) {
            // Add task to tasks
            Task task = Task.toTask(lines[i]);
            addTask(task);
        }
    }

    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints all members of the tasks
     */
    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        int tasksLength = tasks.size();
        for (int index = 0; index < tasksLength; index++) {
            int currentIndex = index + 1;
            System.out.println(currentIndex + "." + tasks.get(index));
        }
    }

    /**
     * Print a confirmation message for adding the task to tasks
     *
     * @param task the Task object that has been added
     */
    private static void printTaskAdditionMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a Task object to tasks
     *
     * @param task the Task object to be added
     * @throws IOException If an input/output exception occurs
     */
    private static void addTask(Task task) throws IOException {
        tasks.add(task);

        // Update saved tasks
        overwriteTasksFile(FILE_PATH);
    }

    /**
     * Set isDone attribute of element of type task
     * with the index number of indexNumber to true
     * and prints confirmation message
     *
     * @param indexNumber the index number of the element of type task
     *                    to set isDone attribute
     * @throws BenMarkAlreadyDoneException If the task has already been marked as done
     * @throws BenIndexOutOfRangeException If indexNumber exceeds the current length of tasks
     * @throws IOException If tasks saving process failed due to Input/Output exception
     */
    private static void markAndPrintTaskDone(int indexNumber)
            throws BenMarkAlreadyDoneException, BenIndexOutOfRangeException, IOException {
        /* If indexNumber is outside of the length of tasks,
           throw an exception
         */
        if (indexNumber > tasks.size()) {
            throw new BenIndexOutOfRangeException(indexNumber);
        }

        // Obtain index and task
        int index = indexNumber - 1;
        Task task = tasks.get(index);

        // Set the element as done
        task.markAsDone();

        // Print message to confirm
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);

        // Update saved tasks
        overwriteTasksFile(FILE_PATH);
    }

    /**
     * Set isDone attribute of element of type task
     * with the index number of indexNumber to false
     * and prints confirmation message
     *
     * @param indexNumber the index number of the element of type task
     *                    to set isDone attribute
     * @throws BenMarkAlreadyNotDoneException If the task has already been marked as not done
     * @throws BenIndexOutOfRangeException If indexNumber exceeds the current length of tasks
     * @throws IOException If tasks saving process failed due to Input/Output exception
     */
    private static void markAndPrintTaskUndone(int indexNumber)
            throws BenMarkAlreadyNotDoneException, BenIndexOutOfRangeException, IOException {
        /* If indexNumber is outside of the length of tasks,
           throw an exception
         */
        if (indexNumber > tasks.size()) {
            throw new BenIndexOutOfRangeException(indexNumber);
        }

        // Obtain index and task
        int index = indexNumber - 1;
        Task task = tasks.get(index);

        // Set the element as not done
        task.markAsUndone();

        // Print message to confirm
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  " + task);

        // Update saved tasks
        overwriteTasksFile(FILE_PATH);
    }

    /**
     * Delete the task found on the position indexNumber of tasks.
     *
     * @param indexNumber the index number of the element of type task
     *                    to delete from tasks.
     * @throws BenIndexOutOfRangeException If indexNumber exceeds the current length of tasks.
     * @throws IOException If tasks saving process failed due to Input/Output exception.
     */
    private static void deleteAndPrintTaskDeleted(int indexNumber)
            throws BenIndexOutOfRangeException, IOException {
        /* If indexNumber is outside of the length of tasks,
           throw an exception
         */
        if (indexNumber > tasks.size()) {
            throw new BenIndexOutOfRangeException(indexNumber);
        }

        // Obtain index and task
        int index = indexNumber - 1;
        Task task = tasks.get(index);

        // Remove task from tasks
        tasks.remove(index);

        // Print message to confirm
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        // Update saved tasks
        overwriteTasksFile(FILE_PATH);
    }

    public static void main(String[] args) {
        // Hard disk save initialization or setup
        try {
            String content = initializeRawDataFromPath(FILE_PATH);
            loadSavedTasks(content);
        } catch (FileNotFoundException e) {
            // Never thrown if application logic is correct
            System.out.println("The file to save to is not found.");
        } catch (IOException e) {
            System.out.println("Input/Output error caused saved tasks loading.");
        } catch (BenException e) {
            System.out.println(e);
        }

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

                        // Mark done
                        markAndPrintTaskDone(indexNumber);
                    } catch (NumberFormatException e) {
                        throw new BenInvalidParameterException(commandParameters[1]);
                    }
                } else if (command.equals("unmark")) {
                    try {
                        // Obtain indexNumber
                        int indexNumber = Integer.parseInt(commandParameters[1]);

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

                    // Add to tasks
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

                    // Add to tasks
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

                    // Add to tasks
                    addTask(eventTask);

                    // Print confirmation message
                    printTaskAdditionMessage(eventTask);
                } else if (command.equals("delete")) {
                    try {
                        // Obtain indexNumber
                        int indexNumber = Integer.parseInt(commandParameters[1]);

                        // Delete task with indexNumber
                        deleteAndPrintTaskDeleted(indexNumber);
                    } catch (NumberFormatException e) {
                        throw new BenInvalidParameterException(commandParameters[1]);
                    }
                } else {
                    // If it does not match any command, throw an exception
                    throw new BenInvalidCommandException();
                }
            } catch (BenException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println("Tasks saving failed due to input/output error.");
            }

            printLine();
        }

        // Exit dialogue
        System.out.println("Bye. Hope to see you again soon!");

        printLine();
    }
}
