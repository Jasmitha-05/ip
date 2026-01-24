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
                    case "list" -> taskList.DisplayAllTasks();
                    case "mark" -> taskList.MarkTask(Integer.parseInt(parsedInput[1]));
                    case "unmark" -> taskList.UnmarkTask(Integer.parseInt(parsedInput[1]));
                    case "todo" -> taskList.ToDoTask(parsedInput[1]);
                    case "deadline" -> taskList.DeadlineTask(parsedInput[1], parsedInput[2]);
                    case "event" -> taskList.EventTask(parsedInput[1], parsedInput[2], parsedInput[3]);
                    case "delete" -> taskList.DeleteTask(Integer.parseInt(parsedInput[1]));
                    case "search" -> taskList.SameDateTask(parsedInput[1]);
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
