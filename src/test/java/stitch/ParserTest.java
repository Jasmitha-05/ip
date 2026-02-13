package stitch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.beans.Transient;

import org.junit.jupiter.api.Test;

/**
 * Test non-trivial methods in Parser class using JUnit and Gradle
 */
public class ParserTest {

    @Test
    void parse_deadline_success() throws StitchException {
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

    @Test
    void parse_upcoming_success() throws StitchException {
        String[] parsed = Parser.parse("upcoming 3");
        assertArrayEquals(new String[] { "upcoming", "3" }, parsed);
    }

    @Test
    void parse_upcoming_invalidNumber_exceptionThrown() { //Use ChatGPT to generate possible edge case for upcoming command 
        StitchException e = assertThrows(StitchException.class, () -> {
            Parser.parse("upcoming three");
        });
        assertEquals("OOPS! not a valid number of days. Was it a mistake?", e.getMessage());
    }

    @Test
    void parse_caseInsensitive_success() throws StitchException { //Use ChatGPT to generate test case for case insensitivity of commands
        String[] parsedTodo = Parser.parse("ToDo return book");
        assertArrayEquals(new String[] { "todo", "return book" }, parsedTodo);

        String[] parsedDeadline = Parser.parse("DeAdLine t1 /by 2025-12-3 15:00");
        assertArrayEquals(new String[] { "deadline", "t1", "2025-12-3 15:00" }, parsedDeadline);

        String[] parsedEvent = Parser.parse("EvEnT project /from 2025-12-1 2:00 /to 2025-12-3 15:00");
        assertArrayEquals(new String[] { "event", "project", "2025-12-1 2:00", "2025-12-3 15:00" }, parsedEvent);

        String[] parsedMark = Parser.parse("MaRk 2");
        assertArrayEquals(new String[] { "mark", "1" }, parsedMark);
    }

    @Test
    void parse_inputWithExtraSpaces_success() throws StitchException {
        String[] parsed = Parser.parse("   deadline    return book   /by   2025-11-25 16:01   ");
        assertArrayEquals(new String[] { "deadline", "return book", "2025-11-25 16:01" }, parsed);
    }

    @Test
    void parse_inputPartOfCommand_exceptionThrown() {
        try {
            String[] parsed = Parser.parse("markus 2");
            fail();
        } catch (StitchException e) {
            assertEquals("I'm sorry, I don't understand.", e.getMessage());
        }
    }
}
