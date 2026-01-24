import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    public TaskList(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = storage.load();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void DisplayAllTasks() {
        ui.showAllTask(tasks, tasks.size());
    }

    public void MarkTask(int order) throws StitchException {
        if (tasks.size() == 0) {
                throw new StitchException("OOPS! no more task to mark.");
        }

        if (order < 0 || order >= tasks.size()) {
            throw new StitchException("OOPS! the number is invalid.");
        }

        tasks.get(order).markAsDone();
        storage.save(tasks);
        ui.showMarkTask(tasks.get(order));
    }

    public void UnmarkTask(int order) throws StitchException {
        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no more task to unmark.");
        } 
        
        if (order < 0 || order >= tasks.size()) {
            throw new StitchException("OOPS! the number is invalid.");
        }

        tasks.get(order).markAsUnDone();
        storage.save(tasks); 
        ui.showUnmarkTask(tasks.get(order));
    }

    public void ToDoTask(String description) throws StitchException {
        if (description.isEmpty()) {
            throw new StitchException("OOPS! you forgot to name the todo task");
        }

        Task newTask = new ToDo(description);
        tasks.add(newTask);
        storage.save(tasks);
        ui.showAddTask(newTask, tasks.size());
    }

    public void DeadlineTask(String description, String by) throws StitchException {
        if (description.isEmpty()) {
            throw new StitchException("OOPS! you forget to add the deadline task");
        }

        if(by.isEmpty()) {
            throw new StitchException("OOPS! you forget to add the deadline date and time");
        }

        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        storage.save(tasks); 
        ui.showAddTask(newTask, tasks.size());
    }

    public void EventTask(String description, String from, String to) throws StitchException {
        if (description.isEmpty()) {
            throw new StitchException("OOPS! you forget to add the event task");
        }
        if (from.isEmpty()) {
            throw new StitchException("OOPS! you forget to add the event start date and time");
        }
        if (to.isEmpty()) {
            throw new StitchException("OOPS! you forget to add the event end date and time");
        }

        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        storage.save(tasks); 
        ui.showAddTask(newTask, tasks.size());
    }

    public void DeleteTask(int order) throws StitchException {
        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no task to delete.");
        }
        if (order < 0 || order >= tasks.size()) {
            throw new StitchException("OOPS! the number is invalid.");
        }
        
        Task deletedTask = tasks.get(order);
        tasks.remove(order);
        storage.save(tasks); 
        ui.showDeleteTask(deletedTask, tasks.size());
    }

    public void SameDateTask(String date) throws StitchException {
        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no tasks currently.");
        } 

        DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("yyyy-M-d");
        DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate searchDate;
        boolean present = false;
        
        try {
            searchDate = LocalDate.parse(date, INPUT);
        } catch (DateTimeParseException e) {
            throw new StitchException("OOPS! wrong format, use format: yyyy-M-d");
        }

        System.out.println("     ______________________________");
        System.out.println("     Got it. Tasks on " + searchDate.format(OUTPUT) + ":");


        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                LocalDate taskDate = ((Deadline) tasks.get(i)).by.toLocalDate();
                if (taskDate.equals(searchDate)) {
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
