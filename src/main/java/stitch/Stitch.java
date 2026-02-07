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

    /** Constructs a Stitch instance. */
    public Stitch() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(ui, storage);
    }

    public Ui getUi() {
        return ui;
    }    

    /**
     * Handles user input and executes commands based on the parsed input.
     * 
     * @param userInput the input string from the user
     * @return the response string to be displayed
     */
    public String getResponse(String userInput) {
        try {
            String[] parsedInput = Parser.parse(userInput);
            assert parsedInput != null : "Parser returning null";
            assert parsedInput.length > 0 : "no command parsed";

            switch (parsedInput[0]) {
            case "list":
                return taskList.displayAllTasks();
            case "mark":
                assert parsedInput.length == 2 : "wrong format for mark";
                return taskList.markTask(Integer.parseInt(parsedInput[1]));
            case "unmark":
                assert parsedInput.length == 2 : "wrong format for unmark";
                return taskList.unmarkTask(Integer.parseInt(parsedInput[1]));
            case "todo":
                assert parsedInput.length == 2 : "wrong format for todo";
                return taskList.todoTask(parsedInput[1]);
            case "deadline":
                assert parsedInput.length == 3 : "wrong format for deadline";
                return taskList.deadlineTask(parsedInput[1], parsedInput[2]);
            case "event":
                assert parsedInput.length == 4 : "wrong format for event";
                return taskList.eventTask(parsedInput[1], parsedInput[2], parsedInput[3]);
            case "delete":
                assert parsedInput.length == 3 : "wrong format for delete";
                return taskList.deleteTask(Integer.parseInt(parsedInput[1]));
            case "search":
                assert parsedInput.length == 2 : "wrong format for search";
                return taskList.sameDateTask(parsedInput[1]);
            case "find":
                assert parsedInput.length == 2 : "wrong format for find";
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
