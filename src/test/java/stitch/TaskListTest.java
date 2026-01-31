package stitch;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Test non-trivial methods in TaskList class just JUnit and Gradle
 */
public class TaskListTest {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    @Test
    public void findTask_keywordFound() throws StitchException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        taskList.eventTask("project meeting", "2025-12-1 2:00", "2025-12-3 15:00");
        taskList.eventTask("group meeting", "2026-12-1 2:00", "2026-12-3 15:00");
        assertDoesNotThrow(() -> taskList.findTask("meeting"));
    }

    @Test
    public void sameDateTask_wrongFormat_exceptionThrown() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        try {
            taskList.sameDateTask("24-12-2026");
            fail();
        } catch (StitchException e) {
            assertEquals("OOPS! wrong format, use format: yyyy-M-d", e.getMessage());
        }
    }

    @Test
    public void sameDateTask_correctFormat_success() throws StitchException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        taskList.deadlineTask("t1", "2025-12-2 16:01");
        taskList.eventTask("t2", "2025-12-1 2:00", "2025-12-3 15:00");
        assertDoesNotThrow(() -> taskList.sameDateTask("2025-12-2"));
    }

    @Test
    public void markTask_wrongNumber_exceptionThrown() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        try {
            taskList.todoTask("t1");
            taskList.markTask(0);
            fail();
        } catch (StitchException e) {
            assertEquals("OOPS! the number is invalid.", e.getMessage());
        }
    }

    @Test
    public void markTask_correctNumber_success() throws StitchException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        taskList.deadlineTask("t1", "2025-12-2 16:01");
        taskList.eventTask("t2", "2025-12-1 2:00", "2025-12-3 15:00");
        assertDoesNotThrow(() -> taskList.markTask(1));
    }

    @Test
    public void unmarkTask_wrongNumber_exceptionThrown() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        try {
            taskList.todoTask("t1");
            taskList.unmarkTask(0);
            fail();
        } catch (StitchException e) {
            assertEquals("OOPS! the number is invalid.", e.getMessage());
        }
    }

    @Test
    public void unmarkTask_correctNumber_success() throws StitchException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        taskList.deadlineTask("t1", "2025-12-2 16:01");
        taskList.eventTask("t2", "2025-12-1 2:00", "2025-12-3 15:00");
        assertDoesNotThrow(() -> taskList.unmarkTask(1));
    }

    @Test
    public void deleteTask_wrongNumber_exceptionThrown() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        try {
            taskList.todoTask("t1");
            taskList.deleteTask(0);
            fail();
        } catch (StitchException e) {
            assertEquals("OOPS! the number is invalid.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_correctNumber_success() throws StitchException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(ui, storage);

        taskList.deadlineTask("t1", "2025-12-2 16:01");
        taskList.eventTask("t2", "2025-12-1 2:00", "2025-12-3 15:00");
        assertDoesNotThrow(() -> taskList.deleteTask(1));
    }
}
