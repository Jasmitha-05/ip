package stitch;

import java.util.Scanner;

/**
 * main class of the Stitch chatbot application.
 * Handles user input and executes based on command types (e.g., list, mark,
 * unmark, add, delete, search, bye).
 */
public class Stitch {
    public static void main(String[] args) {
        String chatBotName = "Stitch";
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui, storage);

        ui.showGreet(chatBotName);

        while (true) {

            String userInput = scanner.nextLine().trim();
            try {
                String[] parsedInput = Parser.parse(userInput);
                switch (parsedInput[0]) {
                    case "list" -> taskList.displayAllTasks();
                    case "mark" -> taskList.markTask(Integer.parseInt(parsedInput[1]));
                    case "unmark" -> taskList.unmarkTask(Integer.parseInt(parsedInput[1]));
                    case "todo" -> taskList.todoTask(parsedInput[1]);
                    case "deadline" -> taskList.deadlineTask(parsedInput[1], parsedInput[2]);
                    case "event" -> taskList.eventTask(parsedInput[1], parsedInput[2], parsedInput[3]);
                    case "delete" -> taskList.deleteTask(Integer.parseInt(parsedInput[1]));
                    case "search" -> taskList.sameDateTask(parsedInput[1]);
                    case "bye" -> {
                        ui.showBye();
                        scanner.close();
                        return;
                    }
                    default -> ui.showErrorMessage("I'm sorry, I don't understand.");
                }
            } catch (StitchException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
