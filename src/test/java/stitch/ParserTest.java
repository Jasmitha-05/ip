package stitch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test non-trivial methods in Parser class just JUnit and Gradle
 */
public class ParserTest {

    @Test
    public void parse_deadline_success() throws StitchException {
        String[] parsed = Parser.parse("deadline return book /by 2025-11-25 16:01");
        assertArrayEquals(new String[] { "deadline", "return book", "2025-11-25 16:01" }, parsed);
    }

    @Test
    void parse_event_success() throws StitchException {
        String[] parsed = Parser.parse("event project meeting /from 2025-12-1 2:00 /to 2025-12-3 15:00");
        assertArrayEquals(new String[] { "event", "project meeting", "2025-12-1 2:00", "2025-12-3 15:00" }, parsed);
    }

    @Test
    void parse_mark_success() throws StitchException {
        String[] parsed = Parser.parse("mark 2");
        assertArrayEquals(new String[] { "mark", "1" }, parsed);
    }

    @Test
    void parse_unmark_success() throws StitchException {
        String[] parsed = Parser.parse("unmark 2");
        assertArrayEquals(new String[] { "unmark", "1" }, parsed);
    }

    @Test
    void parse_delete_success() throws StitchException {
        String[] parsed = Parser.parse("delete 2");
        assertArrayEquals(new String[] { "delete", "1" }, parsed);
    }

    @Test
    void parse_find_success() throws StitchException {
        String[] parsed = Parser.parse("find hello");
        assertArrayEquals(new String[] { "find", "hello" }, parsed);
    }

    @Test
    void parse_search_success() throws StitchException {
        String[] parsed = Parser.parse("search 2025-11-25");
        assertArrayEquals(new String[] { "search", "2025-11-25" }, parsed);
    }

    @Test
    void parse_todoEmptyDescription_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("todo ");
            fail();
        } catch (StitchException e) {
            assertEquals("OOPS! did you forget to add the name of the todo task?", e.getMessage());
        }
    }

    @Test
    void parse_deadlineEmptyDescription_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("deadline /by 2025-11-25 16:01 ");
            fail();
        } catch (StitchException e) {
            assertEquals(
                    "OOPS! wrong format. Use the format: deadline (task) /by (yyyy-M-d H:m)",
                    e.getMessage());
        }
    }

    @Test
    void parse_deadlineEmptyDate_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("deadline t1 /by ");
            fail();
        } catch (StitchException e) {
            assertEquals(
                    "OOPS! wrong format. Use the format: deadline (task) /by (yyyy-M-d H:m)",
                    e.getMessage());
        }
    }

    @Test
    void parse_eventEmptyEndDate_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("event project meeting /from 2025-12-1 2:00 /to ");
            fail();
        } catch (StitchException e) {
            assertEquals(
                    "OOPS! wrong format. Use the format: event (task) /from (yyyy-M-d H:m) /to (yyyy-M-d H:m)",
                    e.getMessage());
        }
    }

    @Test
    void parse_eventEmptyStartDate_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("event project meeting/from/to 2025-12-3 15:00");
            fail();
        } catch (StitchException e) {
            assertEquals(
                    "OOPS! wrong format. Use the format: event (task) /from (yyyy-M-d H:m) /to (yyyy-M-d H:m)",
                    e.getMessage());
        }
    }

    @Test
    void parse_eventWrongFormat_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("event project meeting/from 2025-12-3 15:00to/ 2025-12-3 15:00");
            fail();
        } catch (StitchException e) {
            assertEquals(
                    "OOPS! wrong format. Use the format: event (task) /from (yyyy-M-d H:m) /to (yyyy-M-d H:m)",
                    e.getMessage());
        }
    }

    @Test
    void parse_deadlineWrongFormat_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("deadline t1 by/ 2025-12-3 15:00 ");
            fail();
        } catch (StitchException e) {
            assertEquals(
                    "OOPS! wrong format. Use the format: deadline (task) /by (yyyy-M-d H:m)",
                    e.getMessage());
        }
    }

}
