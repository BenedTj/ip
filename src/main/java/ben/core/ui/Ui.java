package ben.core.ui;

import ben.exception.BenException;
import ben.task.Task;

/**
 * The class for the base UI functionality
 * without any printing.
 */
public class Ui {
    /**
     * Returns the welcome message.
     *
     * @return The welcome message as a string.
     */
    public String showWelcomeBase() {
        // Construct message
        StringBuilder welcomeMessageBuilder = new StringBuilder("Hello! I'm Ben.");
        welcomeMessageBuilder.append(System.lineSeparator());
        welcomeMessageBuilder.append("What can I do for you?");

        return welcomeMessageBuilder.toString();
    }

    /**
     * Returns the message passed.
     *
     * @param message The message to be returned.
     * @return The message passed.
     */
    public String showMessageBase(String message) {
        return message;
    }

    /**
     * Returns the message for added task.
     *
     * @param task The task that was added.
     * @param tasksSize The current size or length of tasks.
     * @return The message for added task.
     */
    public String showTaskAddedMessageBase(Task task, int tasksSize) {
        // Construct message
        StringBuilder tasksMessageBuilder = new StringBuilder("Got it. I've added this task:");
        tasksMessageBuilder.append(System.lineSeparator());
        tasksMessageBuilder.append(" " + task);
        tasksMessageBuilder.append(System.lineSeparator());
        tasksMessageBuilder.append("Now you have " + tasksSize + " tasks in the list.");

        return tasksMessageBuilder.toString();
    }

    /**
     * Returns the message for a BenException.
     *
     * @param benException The BenException whose error message
     *                     should be printed.
     * @return The message for a BenException.
     */
    public String showBenExceptionBase(BenException benException) {
        return benException.toString();
    }
}
