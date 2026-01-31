package stitch;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a description, from date and time, to date and
 * time (format: yyyy-M-d H:m).
 * Extends the Task class.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with given description, from and to date and time.
     * 
     * @param description title of the Event task.
     * @param from        starting date and time of the event.
     * @param to          ending date and time of the event.
     * @throws StitchException if date and time is invalid format.
     */
    public Event(String description, String from, String to) throws StitchException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.trim(), INPUT);
            this.to = LocalDateTime.parse(to.trim(), INPUT);
        } catch (DateTimeParseException e) {
            throw new StitchException("OOPS! Please use the format yyyy-M-d H:m");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT)
                + " to: " + to.format(OUTPUT) + ")";
    }

}
