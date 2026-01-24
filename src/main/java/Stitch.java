import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stitch {
    public static void main(String[] args) {
        String chatBotName = "Stitch";
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        ArrayList<Task> tasks = storage.load();
        int numOfTasks = tasks.size();

        Ui ui = new Ui();
        ui.showGreet(chatBotName);

        while(true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) { //Exit
                ui.showBye();
                break;
            }
            
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    DisplayAllTasks(tasks, numOfTasks);
                } else if (userInput.startsWith("mark")) {
                    numOfTasks = MarkTask(tasks, userInput, numOfTasks, storage);
                } else if (userInput.startsWith("unmark")) {
                    numOfTasks = UnmarkTask(tasks, userInput, numOfTasks, storage);
                } else if (userInput.startsWith("todo")) {
                    numOfTasks = ToDoTask(tasks, userInput, numOfTasks, storage);
                } else if (userInput.startsWith("deadline")) {
                    numOfTasks = DeadlineTask(tasks, userInput, numOfTasks, storage);
                } else if (userInput.startsWith("event")) {
                    numOfTasks = EventTask(tasks, userInput, numOfTasks, storage);
                } else if (userInput.startsWith("delete")) {
                    numOfTasks = DeleteTask(tasks, userInput, numOfTasks, storage);
                } else if (userInput.startsWith("search")) {
                    SameDateTask(tasks, userInput, numOfTasks);
                } else {
                    throw new StitchException("I'm sorry, I don't understand.");
                }
            } catch (StitchException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
         scanner.close();
    }

    private static void DisplayAllTasks(ArrayList<Task> tasks, int numOfTasks) {
        Ui ui = new Ui();
        ui.showAllTask(tasks, numOfTasks);
    }

    private static int MarkTask(ArrayList<Task> tasks, String userInput, int numOfTasks, Storage storage) throws StitchException {
        if (numOfTasks == 0) {
                throw new StitchException("OOPS! no more task to mark.");
        }
        
        try {
            
            int order = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (order < 0 || order >= tasks.size()) {
                throw new StitchException("OOPS! the number is invalid.");
            }

            tasks.get(order).markAsDone();
            storage.save(tasks); 

            Ui ui = new Ui();
            ui.showMarkTask(tasks.get(order));
            return numOfTasks;

            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
            }
    }

    private static int UnmarkTask(ArrayList<Task> tasks, String userInput, int numOfTasks, Storage storage) throws StitchException {
        if (numOfTasks == 0) {
            throw new StitchException("OOPS! no more task to unmark.");
        } 
        
        try {
            int order = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (order < 0 || order >= tasks.size()) {
                throw new StitchException("OOPS! the number is invalid.");
            }

            tasks.get(order).markAsUnDone();
            storage.save(tasks); 

            Ui ui = new Ui();
            ui.showUnmarkTask(tasks.get(order));
            return numOfTasks;

            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
           }
    }

    private static int ToDoTask(ArrayList<Task> tasks, String userInput, int numOfTasks, Storage storage) throws StitchException {
        String removeToDOString = userInput.replaceFirst("todo", "").trim();

        if (removeToDOString.isEmpty()) {
            throw new StitchException("OOPS! you forgot to name the todo task");
        }

        Task newTask = new ToDo(removeToDOString);
        tasks.add(newTask);
        storage.save(tasks); 
        numOfTasks++;
        Ui ui = new Ui();
        ui.showAddTask(newTask, tasks.size());
        return tasks.size();
    }

    private static int DeadlineTask(ArrayList<Task> tasks, String userInput, int numOfTasks, Storage storage) throws StitchException {
        String removeToDOString = userInput.replaceFirst("deadline ", "");
        String[] splitInput = removeToDOString.split(" /by");

        if(splitInput.length < 2 || splitInput[0].trim().isEmpty() || splitInput[1].trim().isEmpty()) {
            throw new StitchException("OOPS! did you forget to add the task name or time?");
        }

        Task newTask = new Deadline(splitInput[0], splitInput[1]);
        tasks.add(newTask);
        storage.save(tasks); 
        numOfTasks++;
        Ui ui = new Ui();
        ui.showAddTask(newTask, tasks.size());
        return tasks.size();
    }

    private static int EventTask(ArrayList<Task> tasks, String userInput, int numOfTasks, Storage storage) throws StitchException {
        String removeToDOString = userInput.replaceFirst("event ", "");
        String[] splitInput = removeToDOString.split(" /from| /to");

        if(splitInput.length < 3 || splitInput[0].trim().isEmpty() || splitInput[1].trim().isEmpty() || splitInput[2].trim().isEmpty()) {
            throw new StitchException("OOPS! did you forget to add the task name or timing?");
        }

        Task newTask = new Event(splitInput[0], splitInput[1], splitInput[2]);
        tasks.add(newTask);
        storage.save(tasks); 
        numOfTasks++;
        Ui ui = new Ui();
        ui.showAddTask(newTask, tasks.size());
        return tasks.size();
    }

    private static int DeleteTask(ArrayList<Task> tasks, String userInput, int numOfTasks, Storage storage) throws StitchException {
        if (numOfTasks == 0) {
            throw new StitchException("OOPS! no task to delete.");
        }
        String[] splitInput = userInput.split(" ");

        if (splitInput.length < 2 || splitInput[1].trim().isEmpty()) {
            throw new StitchException("OOPS! did you forget to indicate which task to delete?");
        }
                        
        try {
            int order = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (order < 0 || order >= tasks.size()) {
                throw new StitchException("OOPS! the number is invalid.");
            }

            Task deletedTask = tasks.get(order);
            tasks.remove(order);
            storage.save(tasks); 
            numOfTasks--;
            Ui ui = new Ui();
            ui.showDeleteTask(deletedTask, tasks.size());
            return tasks.size();

            } catch (NumberFormatException e) {
                throw new StitchException("OOPS! not a valid number. Maybe it's a mistake?");
            }
    }
    
    private static void SameDateTask(ArrayList<Task> tasks, String userInput, int numOfTasks) throws StitchException {
        if (numOfTasks == 0) {
            throw new StitchException("OOPS! no tasks currently.");
        } 

        String removeToDOString = userInput.replaceFirst("search", "").trim();
        DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("yyyy-M-d");
        DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate searchDate;
        boolean present = false;
        
        if (removeToDOString.isEmpty()) {
            throw new StitchException("OOPS! you forgot to add the date");
        }
        
        try {
            searchDate = LocalDate.parse(removeToDOString, INPUT);
        } catch (DateTimeParseException e) {
            throw new StitchException("OOPS! wrong format, use format: yyyy-M-d");
        }

        System.out.println("     ______________________________");
        System.out.println("     Got it. Tasks on " + searchDate.format(OUTPUT) + ":");


        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                LocalDate date = ((Deadline) tasks.get(i)).by.toLocalDate();
                if (date.equals(searchDate)) {
                    present = true;
                    System.out.println("     " + tasks.get(i).toString());
                }
            
            } else if (tasks.get(i) instanceof Event) {
                LocalDate from = ((Event) tasks.get(i)).from.toLocalDate();
                LocalDate to = ((Event) tasks.get(i)).to.toLocalDate();
                if (from.equals(searchDate) || to.equals(searchDate) || (searchDate.isAfter(from) && searchDate.isBefore(to))) {
                    present = true;
                    System.out.println("     " + tasks.get(i).toString());
                }
            } else {
                continue;
            }      
        }
        if (present != true) {
            System.out.println("     YAYYY no tasks!!");
        }
        System.out.println("     ______________________________");
    }
}