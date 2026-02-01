package stitch;

/**
 * main class of the Stitch chatbot application.
 * Handles user input and executes based on command types (e.g., list, mark,
 * unmark, add, delete, search, bye).
 */
public class Stitch {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    public Stitch() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(ui, storage);
    }

    public String getResponse(String userInput) {
        try {
            String[] parsedInput = Parser.parse(userInput);

            switch (parsedInput[0]) {
            case "list":
                return taskList.displayAllTasks();
            case "mark":
                return taskList.markTask(Integer.parseInt(parsedInput[1]));
            case "unmark":
                return taskList.unmarkTask(Integer.parseInt(parsedInput[1]));
            case "todo":
                return taskList.todoTask(parsedInput[1]);
            case "deadline":
                return taskList.deadlineTask(parsedInput[1], parsedInput[2]);
            case "event":
                return taskList.eventTask(parsedInput[1], parsedInput[2], parsedInput[3]);
            case "delete":
                return taskList.deleteTask(Integer.parseInt(parsedInput[1]));
            case "search":
                return taskList.sameDateTask(parsedInput[1]);
            case "find":
                return taskList.findTask(parsedInput[1]);
            case "bye":
                return ui.showBye();
            default:
                return ui.showErrorMessage("I'm sorry, I don't understand.");
            }
        } catch (StitchException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }
}
