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
    private static final String UPCOMING = "upcoming";
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
        if (userInput.startsWith(UPCOMING)) {
            return parseUpcomingCommand(userInput);
        }
        throw new StitchException("I'm sorry, I don't understand.");
    }

    /**
     * Parses the list command.
     * 
     * @param list the list command string
     * @return an String array containing the list command
     */
    private static String[] parseListCommand(String list) {
        return new String[] { list };
    }

    /**
     * Parses the bye command.
     * 
     * @param bye the bye command string
     * @return an String array containing the bye command
     */
    private static String[] parseByeCommand(String bye) {
        return new String[] { bye };
    }

    /**
     * Parses the delete/mark/unmark command.
     * 
     * @param command   the delete/mark/unmark command string
     * @param userInput the input from user
     * @return an String array containing the command
     * @throws StitchException if the index is not valid or missing
     */
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

    /**
     * Parses the todo command.
     * 
     * @param userInput the input from user
     * @return an String array containing the todo command
     * @throws StitchException if the description is missing
     */
    private static String[] parseTodoCommand(String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + TODO + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the name of the todo task?");
        return new String[] { TODO, removeCommand };
    }

    /**
     * Parses the deadline command.
     * 
     * @param userInput the input from user
     * @return an String array containing the deadline command
     * @throws StitchException if wrong date format or missing
     */
    private static String[] parseDeadlineCommand(String userInput) throws StitchException {
        String[] removeCommand = userInput.replaceFirst("(?i)" + DEADLINE + "\\s*", "")
                .trim().split("\\s*/by\\s*");

        if (removeCommand.length < 2 || removeCommand[0].trim().isEmpty()
                || removeCommand[1].trim().isEmpty()) {
            throw new StitchException("OOPS! wrong format. Use the format: deadline (task) /by (yyyy-M-d H:m)");
        }
        return new String[] { DEADLINE, removeCommand[0].trim(), removeCommand[1].trim() };
    }

    /**
     * Parses the event command.
     * 
     * @param userInput the input from user
     * @return an String array containing the event command
     * @throws StitchException if wrong date format or missing
     */
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

    /**
     * Parses the search command.
     * 
     * @param userInput the input from user
     * @return an String array containing the search command
     * @throws StitchException if wrong date format or missing
     */
    private static String[] parseSearchCommand(String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + SEARCH + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the search date?");
        return new String[] { SEARCH, removeCommand };

    }

    /**
     * Parses the find command.
     * 
     * @param userInput the input from user
     * @return an String array containing the find command
     * @throws StitchException if keyword ismissing
     */
    private static String[] parseFindCommand(String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + FIND + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the keyword to find?");
        return new String[] { FIND, removeCommand };
    }

    /**
     * Parses the upcoming command.
     * 
     * @param userInput the input from user
     * @return an String array containing the upcoming command
     * @throws StitchException if the number of days is missing or invalid
     */
    private static String[] parseUpcomingCommand(String userInput) throws StitchException {
        String removeCommand = userInput.replaceFirst("(?i)" + UPCOMING + "\\s*", "").trim();
        checkEmpty(removeCommand, "OOPS! did you forget to add the number of days?");

        int days;
        try {
            days = Integer.parseInt(removeCommand);
        } catch (NumberFormatException e) {
            throw new StitchException("OOPS! not a valid number of days. Was it a mistake?");
        }
        return new String[] { UPCOMING, String.valueOf(days) };
    }

    /**
     * Check if string is empty
     * 
     * @param str     the string to be checked
     * @param message the error message to be thrown
     * @throws StitchException if the string is empty
     */
    private static void checkEmpty(String str, String message) throws StitchException {
        if (str.isEmpty()) {
            throw new StitchException(message);
        }
    }
}
