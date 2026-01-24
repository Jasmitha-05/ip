package stitch;

/**
 * Represents a todo task with a description.
 * Extends the Task class.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
