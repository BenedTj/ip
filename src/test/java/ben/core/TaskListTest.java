package ben.core;

import ben.exception.BenIndexOutOfRangeException;
import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;
import ben.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void deleteTask_outOfRangeIndex_benIndexOutOfRangeExceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.deleteTask(2);
            fail();
        } catch (Exception e) {
            assertEquals(new BenIndexOutOfRangeException(2).toString(), e.toString());
        }
    }

    @Test
    public void markDone_markingDoneAlreadyDoneTask_benMarkAlreadyDoneException() {
        try {
            ArrayList<Task> tasksList = new ArrayList<>();
            tasksList.add(new Task("Random task", true));
            TaskList tasks = new TaskList(tasksList);

            tasks.markDone(0);
            fail();
        } catch (Exception e) {
            assertEquals(new BenMarkAlreadyDoneException().toString(), e.toString());
        }
    }

    @Test
    public void markDone_outOfRangeIndex_benIndexOutOfRangeExceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.markDone(2);
            fail();
        } catch (Exception e) {
            assertEquals(new BenIndexOutOfRangeException(2).toString(), e.toString());
        }
    }

    @Test
    public void markNotDone_markingDoneAlreadyNotDoneTask_benMarkAlreadyDoneException() {
        try {
            ArrayList<Task> tasksList = new ArrayList<>();
            tasksList.add(new Task("Random task", false));
            TaskList tasks = new TaskList(tasksList);

            tasks.markNotDone(0);
            fail();
        } catch (Exception e) {
            assertEquals(new BenMarkAlreadyNotDoneException().toString(), e.toString());
        }
    }

    @Test
    public void markNotDone_outOfRangeIndex_benIndexOutOfRangeExceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.markNotDone(2);
            fail();
        } catch (Exception e) {
            assertEquals(new BenIndexOutOfRangeException(2).toString(), e.toString());
        }
    }

    @Test
    public void getTasksRepresentation_emptyTaskList_returnsEmptyString() {
        TaskList tasks = new TaskList();
        assertEquals("", tasks.getTasksRepresentation());
    }
}
