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
            int order;
            try {
                order = Integer.parseInt(userInput.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Was it a mistake?");
            }
            return new String[] { "mark", String.valueOf(order) };
        }

        else if (userInput.startsWith("unmark")) {
            int order;
            try {
                order = Integer.parseInt(userInput.split(" ")[1]) - 1;
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
            String[] removeToDOString = userInput.replaceFirst("deadline ", "").split(" /by");

            if (removeToDOString.length < 2 || removeToDOString[0].trim().isEmpty()
                    || removeToDOString[1].trim().isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the name/date/time of the deadline task?");
            }
            return new String[] { "deadline", removeToDOString[0].trim(), removeToDOString[1].trim() };
        }

        else if (userInput.startsWith("event")) {
            String[] removeToDOString = userInput.replaceFirst("event ", "")
                    .split(" /from| /to");

            if (removeToDOString.length < 3 || removeToDOString[0].trim().isEmpty()
                    || removeToDOString[1].trim().isEmpty() || removeToDOString[2].trim().isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the name/date/timing of the event task?");
            }
            return new String[] { "event", removeToDOString[0].trim(), removeToDOString[1].trim(),
                    removeToDOString[2].trim() };
        }

        else if (userInput.startsWith("delete")) {
            int order;
            try {
                order = Integer.parseInt(userInput.split(" ")[1]) - 1;
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
