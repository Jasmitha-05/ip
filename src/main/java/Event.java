import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from.trim(), INPUT);
        this.to = LocalDateTime.parse(to.trim(), INPUT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT) + " to: " + to.format(OUTPUT) + ")";
    }

}
