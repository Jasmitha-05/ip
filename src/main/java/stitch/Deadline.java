package stitch;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws StitchException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by.trim(), INPUT);
        } catch (DateTimeParseException e) {
            throw new StitchException("OOPS! Please use the format yyyy-M-d H:m");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT) + ")";
    }

}
