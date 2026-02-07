package stitch;

/**
 * Parser class help to format the user commands to easier task execution
 */
public class Parser {

    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String SEARCH = "search";
    private static final String FIND = "find";
    private static final String BYE = "bye";

    /**
     * Returns an array of strings representing the parsed user command.
     * 
     * @param userInput The user input string to be parsed based on the command
     *                  type.
     * @return An array of strings representing the parsed command such as command
     *         type and description.
     * @throws StitchException If the user input is invalid or invalid command.
     */
    public static String[] parse(String userInput) throws StitchException {
        assert userInput != null : "input is null";
        userInput = userInput.trim();

        if (userInput.equalsIgnoreCase(LIST)) {
            return parseListCommand(LIST);
        }
        if (userInput.equalsIgnoreCase(BYE)) {
            return parseByeCommand(BYE);
        }
        if (userInput.startsWith(MARK)) {
            return parseIndexCommand(MARK, userInput);
        }
        if (userInput.startsWith(UNMARK)) {
            return parseIndexCommand(UNMARK, userInput);
        }
        if (userInput.startsWith(DELETE)) {
            return parseIndexCommand(DELETE, userInput);
        }
        if (userInput.startsWith(TODO)) {
            return parseTodoCommand(userInput);
        }
        if (userInput.startsWith(DEADLINE)) {
            return parseDeadlineCommand(userInput);
        }
        if (userInput.startsWith(EVENT)) {
            return parseEventCommand(userInput);
        }
        if (userInput.startsWith(SEARCH)) {
            return parseSearchCommand(userInput);
        }
        if (userInput.startsWith(FIND)) {
            return parseFindCommand(userInput);
        }
        throw new StitchException("I'm sorry, I don't understand.");
    }

    private static String[] parseListCommand(String list) {
        return new String[] { list };
    }

    private static String[] parseByeCommand(String bye) {
        return new String[] { bye };
    }

    private static String[] parseIndexCommand(String command, String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + command + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the number?");

        int order;
        try {
            order = Integer.parseInt(removeCommand) - 1;
        } catch (NumberFormatException e) {
            throw new StitchException("OOPS! not a valid number. Was it a mistake?");
        }
        return new String[] { command, String.valueOf(order) };
    }

    private static String[] parseTodoCommand(String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + TODO + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the name of the todo task?");
        return new String[] { TODO, removeCommand };
    }

    private static String[] parseDeadlineCommand(String userInput) throws StitchException {
        String[] removeCommand = userInput.replaceFirst("(?i)" + DEADLINE + "\\s*", "")
                .trim().split("\\s*/by\\s*");

        if (removeCommand.length < 2 || removeCommand[0].trim().isEmpty()
                || removeCommand[1].trim().isEmpty()) {
            throw new StitchException("OOPS! wrong format. Use the format: deadline (task) /by (yyyy-M-d H:m)");
        }
        return new String[] { DEADLINE, removeCommand[0].trim(), removeCommand[1].trim() };
    }

    private static String[] parseEventCommand(String userInput) throws StitchException {
        String[] removeCommand = userInput.replaceFirst("(?i)" + EVENT + "\\s*", "").trim()
                .split("\\s*/from\\s*|\\s*/to\\s*");

        if (removeCommand.length < 3 || removeCommand[0].trim().isEmpty()
                || removeCommand[1].trim().isEmpty() || removeCommand[2].trim().isEmpty()) {
            throw new StitchException(
                    "OOPS! wrong format. Use the format: event (task) /from (yyyy-M-d H:m) /to (yyyy-M-d H:m)");
        }
        return new String[] { EVENT, removeCommand[0].trim(), removeCommand[1].trim(),
                removeCommand[2].trim() };
    }

    private static String[] parseSearchCommand(String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + SEARCH + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the search date?");
        return new String[] { SEARCH, removeCommand };

    }

    private static String[] parseFindCommand(String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + FIND + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the keyword to find?");
        return new String[] { FIND, removeCommand };
    }

    private static void checkEmpty(String str, String message) throws StitchException {
        if (str.isEmpty()) {
            throw new StitchException(message);
        }
    }
}
