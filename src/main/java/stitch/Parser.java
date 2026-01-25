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
        }

        else if (userInput.startsWith("mark")) {
            String removeToDOString = userInput.replaceFirst("mark", "").trim();
            if (removeToDOString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the number?");
            }

            int order;
            try {
                order = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Was it a mistake?");
            }
            return new String[] { "mark", String.valueOf(order) };
        }

        else if (userInput.startsWith("unmark")) {
            String removeToDOString = userInput.replaceFirst("unmark", "").trim();
            if (removeToDOString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the number?");
            }

            int order;
            try {
                order = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Was it a mistake?");
            }
            return new String[] { "unmark", String.valueOf(order) };
        }

        else if (userInput.startsWith("todo")) {
            String removeToDOString = userInput.replaceFirst("todo", "").trim();

            if (removeToDOString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the name of the todo task?");
            }
            return new String[] { "todo", removeToDOString };
        }

        else if (userInput.startsWith("deadline")) {
            String[] removeToDOString = userInput.replaceFirst("deadline ", "").split("\\s*/by\\s*");

            if (removeToDOString.length < 2 || removeToDOString[0].trim().isEmpty()
                    || removeToDOString[1].trim().isEmpty()) {
                throw new StitchException("OOPS! wrong format. Use the format: deadline (task) /by (yyyy-M-d H:m)");
            }
            return new String[] { "deadline", removeToDOString[0].trim(), removeToDOString[1].trim() };
        }

        else if (userInput.startsWith("event")) {
            String[] removeToDOString = userInput.replaceFirst("event ", "").trim()
                    .split("\\s*/from\\s*|\\s*/to\\s*");

            if (removeToDOString.length < 3 || removeToDOString[0].trim().isEmpty()
                    || removeToDOString[1].trim().isEmpty() || removeToDOString[2].trim().isEmpty()) {
                throw new StitchException(
                        "OOPS! wrong format. Use the format: event (task) /from (yyyy-M-d H:m) /to (yyyy-M-d H:m)");
            }
            return new String[] { "event", removeToDOString[0].trim(), removeToDOString[1].trim(),
                    removeToDOString[2].trim() };
        }

        else if (userInput.startsWith("delete")) {
            String removeToDOString = userInput.replaceFirst("delete", "").trim();
            if (removeToDOString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the number?");
            }

            int order;
            try {
                order = Integer.parseInt(userInput.split("\\s+")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
            }

            return new String[] { "delete", String.valueOf(order) };
        }

        else if (userInput.startsWith("search")) {
            String removeToDOString = userInput.replaceFirst("search", "").trim();
            if (removeToDOString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the search date?");
            }
            return new String[] { "search", removeToDOString };
        }

        else if (userInput.equalsIgnoreCase("bye")) {
            return new String[] { "bye" };
        }

        else if (userInput.startsWith("find")) {
            String removeToDOString = userInput.replaceFirst("find", "").trim();
            if (removeToDOString.isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the search keyword?");
            }
            return new String[] { "find", removeToDOString };
        }

        else {
            throw new StitchException("I'm sorry, I don't understand.");
        }
    }
}
