package ben.task;

import ben.exception.BenInvalidFileFormatException;
import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
}
