package stitch;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

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
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT) + " to: " + to.format(OUTPUT) + ")";
    }

}
