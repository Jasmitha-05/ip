package stitch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list of methods like execute user commands like add, delete,
 * mark, unmark, search and list.
 * Interacts with Storage to load and save tasks, and with Ui to display
 * messages to the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a TaskList with Ui and Storage
     * Loads tasks that was previously saved
     * 
     * @param ui      handles user interface messages
     * @param storage handles loading and saving of tasks
     */
    public TaskList(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = storage.load();
    }

    public String displayAllTasks() {
        return ui.showAllTask(tasks, tasks.size());
    }

    /**
     * Marks a task as done based on its order in the list.
     * 
     * @param order indicates the position of the task in the list (0-indexed).
     * @return String message after marking the task.
     * @throws StitchException if invalid order or no tasks available.
     */
    public String markTask(int order) throws StitchException {
        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no more task to mark.");
        }

        if (order < 0 || order >= tasks.size()) {
            throw new StitchException("OOPS! the number is invalid.");
        }

        tasks.get(order).markAsDone();
        storage.save(tasks);
        return ui.showMarkTask(tasks.get(order));
    }

    /**
     * Unmarks a task as not done based on its order in the list.
     * 
     * @param order indicates the position of the task in the list (0-indexed).
     * @return String message after unmarking the task.
     * @throws StitchException if invalid order or no tasks available.
     */
    public String unmarkTask(int order) throws StitchException {
        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no more task to unmark.");
        }

        if (order < 0 || order >= tasks.size()) {
            throw new StitchException("OOPS! the number is invalid.");
        }

        tasks.get(order).markAsUnDone();
        storage.save(tasks);
        return ui.showUnmarkTask(tasks.get(order));
    }

    /**
     * Adds a ToDo task to the task list.
     * 
     * @param description name of the ToDo task.
     * @return String message after adding the todo task.
     * @throws StitchException if the description is empty.
     */
    public String todoTask(String description) throws StitchException {
        if (description.isEmpty()) {
            throw new StitchException("OOPS! you forgot to name the todo task");
        }

        Task newTask = new ToDo(description);
        tasks.add(newTask);
        storage.save(tasks);
        return ui.showAddTask(newTask, tasks.size());
    }

    /**
     * Adds a Deadline task to the task list.
     * 
     * @param description name of the Deadline task.
     * @param by          deadline date and time.
     * @return String message after adding the deadline task.
     * @throws StitchException if the description/by is empty.
     */
    public String deadlineTask(String description, String by) throws StitchException {
        if (description.isEmpty()) {
            throw new StitchException("OOPS! you forget to add the deadline task");
        }

        if (by.isEmpty()) {
            throw new StitchException("OOPS! you forget to add the deadline date and time");
        }

        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        storage.save(tasks);
        return ui.showAddTask(newTask, tasks.size());
    }

    /**
     * Adds an Event task to the task list.
     * 
     * @param description name of the Event task.
     * @param from        start date and time of the Event task.
     * @param to          end date and time of the Event task.
     * @return String message after adding the event task.
     * @throws StitchException if the description/from/to is empty.
     */
    public String eventTask(String description, String from, String to) throws StitchException {
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
        return ui.showAddTask(newTask, tasks.size());
    }

    /**
     * Deletes a task from the task list based on its order.
     * 
     * @param order indicates the position of the task in the list (0-indexed).
     * @return String message after deletion.
     * @throws StitchException if invalid order or no tasks available.
     */
    public String deleteTask(int order) throws StitchException {
        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no task to delete.");
        }
        if (order < 0 || order >= tasks.size()) {
            throw new StitchException("OOPS! the number is invalid.");
        }

        Task deletedTask = tasks.get(order);
        tasks.remove(order);
        storage.save(tasks);
        return ui.showDeleteTask(deletedTask, tasks.size());
    }

    /**
     * Displays all tasks that occur on the same date.
     * 
     * @param date search for tasks on that date (format: yyyy-M-d).
     * @return String of tasks on that date.
     * @throws StitchException if not tasks or wrong date format.
     */
    public String sameDateTask(String date) throws StitchException {
        ArrayList<Task> sameDateTasks = new ArrayList<Task>();

        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no tasks currently.");
        }

        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate searchDate;

        try {
            searchDate = LocalDate.parse(date, input);
        } catch (DateTimeParseException e) {
            throw new StitchException("OOPS! wrong format, use format: yyyy-M-d");
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                LocalDate taskDate = ((Deadline) tasks.get(i)).by.toLocalDate();
                if (taskDate.equals(searchDate)) {
                    sameDateTasks.add(tasks.get(i));
                }

            } else if (tasks.get(i) instanceof Event) {
                LocalDate from = ((Event) tasks.get(i)).from.toLocalDate();
                LocalDate to = ((Event) tasks.get(i)).to.toLocalDate();
                if (from.equals(searchDate) || to.equals(searchDate)
                        || (searchDate.isAfter(from) && searchDate.isBefore(to))) {
                    sameDateTasks.add(tasks.get(i));
                }
            } else {
                continue;
            }
        }
        return ui.showSameDateTask(sameDateTasks);
    }

    /**
     * Finds and displays tasks that match the input keyword.
     * 
     * @param keyword keyword to search in task descriptions.
     * @return String of matching tasks.
     * @throws StitchException if no tasks available.
     */
    public String findTask(String keyword) throws StitchException {
        if (tasks.size() == 0) {
            throw new StitchException("OOPS! no tasks currently.");
        }

        ArrayList<Task> matches = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(tasks.get(i));
            }
        }
        return ui.showFindTask(matches);
    }
}
