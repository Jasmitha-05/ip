package stitch;

/**
 * Parser class help to format the user commands to easier task execution
 */
public class Parser {

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
        userInput = userInput.trim();

        if (userInput.equalsIgnoreCase("list")) {
            return new String[] { "list" };
        } else if (userInput.startsWith("mark")) {
            String removeToDoString = userInput.replaceFirst("mark", "").trim();
            if (removeToDoString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the number?");
            }

            int order;
            try {
                order = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Was it a mistake?");
            }
            return new String[] { "mark", String.valueOf(order) };
        } else if (userInput.startsWith("unmark")) {
            String removeToDoString = userInput.replaceFirst("unmark", "").trim();
            if (removeToDoString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the number?");
            }

            int order;
            try {
                order = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Was it a mistake?");
            }
            return new String[] { "unmark", String.valueOf(order) };
        } else if (userInput.startsWith("todo")) {
            String removeToDoString = userInput.replaceFirst("todo", "").trim();

            if (removeToDoString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the name of the todo task?");
            }
            return new String[] { "todo", removeToDoString };
        } else if (userInput.startsWith("deadline")) {
            String[] removeToDoString = userInput.replaceFirst("deadline ", "").split("\\s*/by\\s*");

            if (removeToDoString.length < 2 || removeToDoString[0].trim().isEmpty()
                    || removeToDoString[1].trim().isEmpty()) {
                throw new StitchException("OOPS! wrong format. Use the format: deadline (task) /by (yyyy-M-d H:m)");
            }
            return new String[] { "deadline", removeToDoString[0].trim(), removeToDoString[1].trim() };
        } else if (userInput.startsWith("event")) {
            String[] removeToDoString = userInput.replaceFirst("event ", "").trim()
                    .split("\\s*/from\\s*|\\s*/to\\s*");

            if (removeToDoString.length < 3 || removeToDoString[0].trim().isEmpty()
                    || removeToDoString[1].trim().isEmpty() || removeToDoString[2].trim().isEmpty()) {
                throw new StitchException(
                        "OOPS! wrong format. Use the format: event (task) /from (yyyy-M-d H:m) /to (yyyy-M-d H:m)");
            }
            return new String[] { "event", removeToDoString[0].trim(), removeToDoString[1].trim(),
                    removeToDoString[2].trim() };
        } else if (userInput.startsWith("delete")) {
            String removeToDoString = userInput.replaceFirst("delete", "").trim();
            if (removeToDoString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the number?");
            }

            int order;
            try {
                order = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
            }

            return new String[] { "delete", String.valueOf(order) };
        } else if (userInput.startsWith("search")) {
            String removeToDoString = userInput.replaceFirst("search", "").trim();
            if (removeToDoString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the search date?");
            }
            return new String[] { "search", removeToDoString };
        } else if (userInput.equalsIgnoreCase("bye")) {
            return new String[] { "bye" };
        } else if (userInput.startsWith("find")) {
            String removeToDoString = userInput.replaceFirst("find", "").trim();
            if (removeToDoString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the search keyword?");
            }
            return new String[] { "find", removeToDoString };
        } else {
            throw new StitchException("I'm sorry, I don't understand.");
        }
    }
}
