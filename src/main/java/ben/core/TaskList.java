package ben.core;

import java.util.ArrayList;

import ben.exception.BenIndexOutOfRangeException;
import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;
import ben.task.Task;

/**
 * The class that represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes a TaskList object with empty tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initializes a TaskList object with a certain tasks
     *
     * @param tasks The initial list tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the length of tasks.
     *
     * @return the length of tasks.
     */
    public int getTasksLength() {
        return this.tasks.size();
    }

    /**
     * Returns the task stored at the index in tasks.
     *
     * @param index The index whose task is to be returned.
     * @return The Task object stored at the index in tasks.
     * @throws BenIndexOutOfRangeException If the index exceeds index range
     *                                     for tasks.
     */
    public Task getTask(int index) throws BenIndexOutOfRangeException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new BenIndexOutOfRangeException(index);
        }
    }

    /**
     * Adds a Task object to tasks.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the raw string representation of the current tasks.
     *
     * @return The string that contains the representations of the
     *         tasks within tasks.
     */
    public String getTasksRepresentation() {
        int tasksLength = this.tasks.size();

        if (this.tasks.isEmpty()) {
            return "";
        } else {
            // Write each task by ascending order in tasks
            StringBuilder representationBuilder = new StringBuilder(this.tasks.get(0).toRepresentation());

            for (int i = 1; i < tasksLength; i++) {
                // Write each task line by line
                representationBuilder.append(System.lineSeparator());
                representationBuilder.append(this.tasks.get(i).toRepresentation());
            }

            return representationBuilder.toString();
        }
    }

    /**
     * Deletes the task found on the position index of tasks.
     *
     * @param index The index of the element of type task
     *                    to delete from tasks.
     * @throws BenIndexOutOfRangeException If indexNumber exceeds the current length of tasks.
     */
    public void deleteTask(int index) throws BenIndexOutOfRangeException {
        /* If indexNumber is outside of the allowed indices of tasks,
           throw an exception
         */
        if (index >= tasks.size()) {
            throw new BenIndexOutOfRangeException(index);
        }

        // Remove task from tasks
        this.tasks.remove(index);
    }

    /**
     * Sets isDone attribute of element of type task
     * with the index to true.
     *
     * @param index The index of the element of type task
     *              to set isDone attribute.
     * @throws BenMarkAlreadyDoneException If the task has already been marked as done.
     * @throws BenIndexOutOfRangeException If indexNumber exceeds the current length of tasks.
     */
    public void markDone(int index)
            throws BenMarkAlreadyDoneException, BenIndexOutOfRangeException {
        // Obtain task
        Task task = this.getTask(index);

        // Set the element as done
        task.markAsDone();
    }

    /**
     * Sets isDone attribute of element of type task
     * with the index to false.
     *
     * @param index The index of the element of type task
     *              to set isDone attribute.
     * @throws BenMarkAlreadyNotDoneException If the task has already been marked as not done.
     * @throws BenIndexOutOfRangeException If indexNumber exceeds the current length of tasks.
     */
    public void markNotDone(int index)
            throws BenMarkAlreadyNotDoneException, BenIndexOutOfRangeException {
        // Obtain task
        Task task = this.getTask(index);

        // Set the element as not done
        task.markAsUndone();
    }
}
