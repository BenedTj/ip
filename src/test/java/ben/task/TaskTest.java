package ben.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ben.exception.BenInvalidFileFormatException;
import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;

public class TaskTest {
    @Test
    public void toTask_invalidFileLineFormat_benInvalidFileFormatExceptionThrown() {
        try {
            assertEquals(new Task("Random Task"), Task.toTask("?|?|?"));
            fail();
        } catch (Exception e) {
            assertEquals(new BenInvalidFileFormatException("?|?|?").toString(), e.toString());
        }
    }

    @Test
    public void markAsDone_alreadyMarkedDone_benMarkAlreadyDoneExceptionThrown() {
        try {
            Task task = new Task("Random Task", true);
            task.markAsDone();
            fail();
        } catch (Exception e) {
            assertEquals(new BenMarkAlreadyDoneException().toString(), e.toString());
        }
    }

    @Test
    public void markAsUndone_alreadyMarkedNotDone_benMarkAlreadyNotDoneExceptionThrown() {
        try {
            Task task = new Task("Random Task", false);
            task.markAsUndone();
            fail();
        } catch (Exception e) {
            assertEquals(new BenMarkAlreadyNotDoneException().toString(), e.toString());
        }
    }

    @Test
    public void toTask_validNotDoneTask_success() throws Exception {
        Task expected = new Todo("Read book", false);
        Task actual = Task.toTask("T| |Read book");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void toTask_validDoneTask_success() throws Exception {
        Task expected = new Todo("Read book", true);
        Task actual = Task.toTask("T|X|Read book");
        assertEquals(expected.toString(), actual.toString());
    }
}
