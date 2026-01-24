package stitch;

import java.time.format.DateTimeFormatter;

public class Task {

    protected static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("yyyy-M-d H:m");
    protected static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() { // undo task
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
