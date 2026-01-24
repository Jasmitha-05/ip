import java.util.Scanner;

public class Stitch {
    public static void main(String[] args) {
        String chatBotName = "Stitch";
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui, storage);

        ui.showGreet(chatBotName);

        while(true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) { //Exit
                ui.showBye();
                break;
            }
            
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    taskList.DisplayAllTasks();
                } else if (userInput.startsWith("mark")) {
                    try {
                        int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        taskList.MarkTask(order);
                    } catch (NumberFormatException e) {
                        throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
                    }
                } else if (userInput.startsWith("unmark")) {
                    try {
                        int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        taskList.UnmarkTask(order);
                    } catch (NumberFormatException e) {
                        throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
                    }
                } else if (userInput.startsWith("todo")) {
                    String removeToDOString = userInput.replaceFirst("todo", "").trim();
                    taskList.ToDoTask(removeToDOString);
                } else if (userInput.startsWith("deadline")) {
                     String[] removeToDOString = userInput.replaceFirst("deadline ", "").split(" /by");
                     taskList.DeadlineTask(removeToDOString[0].trim(), removeToDOString[1].trim());
                } else if (userInput.startsWith("event")) {
                    String[] removeToDOString = userInput.replaceFirst("event ", "").split(" /from| /to");
                    taskList.EventTask(removeToDOString[0].trim(), removeToDOString[1].trim(), removeToDOString[2].trim());
                } else if (userInput.startsWith("delete")) {
                    try {
                        int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        taskList.DeleteTask(order);
                    } catch (NumberFormatException e) {
                        throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
                    }
                } else if (userInput.startsWith("search")) {
                    String removeToDOString = userInput.replaceFirst("search", "").trim();
                    taskList.SameDateTask(removeToDOString);
                } else {
                    throw new StitchException("I'm sorry, I don't understand.");
                }
            } catch (StitchException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
         scanner.close();
    }
}