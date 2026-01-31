package ben.core;

import ben.exception.BenException;
import ben.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Initializes a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the user's input.
     *
     * @return user input.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a line as a separator for output.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        this.showLine();
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the message.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints message for added task.
     *
     * @param task The task that was added.
     * @param tasksSize The current size or length of tasks.
     */
    public void showTaskAddedMessage(Task task, int tasksSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    /**
     * Prints the error message of a BenException.
     *
     * @param benException The BenException whose error message
     *                     should be printed.
     */
    public void showBenException(BenException benException) {
        System.out.println(benException.toString());
    }
}
