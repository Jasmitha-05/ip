package stitch;

import java.util.ArrayList;

/**
 * Represents the displaying of UI message to display to users.
 */
public class Ui {

    /**
     * Ui message to greet user when chatbot is first started.
     * 
     * @return Greeting message.
     */
    public String showGreet() {
        return "Hello! I'm Stitch\n"
                + "What can I do for you?\n";
    }

    /**
     * Messages a farewell when user wants to exit.
     * 
     * @return Farewell message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Ui message to display all error messages to user.
     * 
     * @param message Error message to be displayed.
     * @return Error message.
     */
    public String showErrorMessage(String message) {
        return message;
    }

    /**
     * Ui message to display the current task being added and the total number of
     * tasks in the list
     * 
     * @param task       The task being added.
     * @param numOfTasks The total number of tasks in the list.
     * @return The message to be displayed.
     */
    public String showAddTask(Task task, int numOfTasks) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + (numOfTasks) + " tasks in the list.";
    }

    /**
     * Ui message to display the current task being deleted and the total number of
     * tasks in the list
     * 
     * @param task       The task being deleted.
     * @param numOfTasks The total number of tasks in the list.
     * @return The message to be displayed.
     */
    public String showDeleteTask(Task task, int numOfTasks) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + (numOfTasks) + " tasks in the list.";
    }

    /**
     * Ui message to display the current task being marked as done
     * 
     * @param task The task being marked as done.
     * @return The message to be displayed.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString();
    }

    /**
     * Ui message to display the current task being unmarked undone.
     * 
     * @param task The task being unmarked undone.
     * @return The message to be displayed.
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task.toString();
    }

    /**
     * Ui message to display all tasks in the list.
     * 
     * @param tasks      The ArrayList of tasks in the list.
     * @param numOfTasks The total number of tasks in the list.
     * @return The message to be displayed.
     */
    public String showAllTask(ArrayList<Task> tasks, int numOfTasks) {
        if (numOfTasks == 0) {
            return "Good job! You have no more tasks.";
        } else {
            StringBuilder sb = new StringBuilder();
            int order = 1;
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(order).append(". ")
                        .append(tasks.get(i).toString())
                        .append("\n");
                order++;
            }
            return "Here are the tasks in your list:\n" + sb.toString();
        }
    }

    /**
     * Ui message to display all matching tasks with the given keyword by user.
     * 
     * @param matches list of tasks containing all tasks with the matching keyword.
     * @return The message to be displayed.
     */
    public String showFindTask(ArrayList<Task> matches) {
        if (matches.isEmpty()) {
            return "No matching task and keywords!!";
        } else {
            int order = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < matches.size(); i++) {
                sb.append(order).append(". ")
                        .append(matches.get(i).toString())
                        .append("\n");
                order++;
            }
            return "Here are the matching tasks in your list:\n" + sb.toString();
        }
    }

    /**
     * Ui message to display all tasks occurring on the same date as given by user.
     * 
     * @param sameDateTasks list of tasks occurring on the same date.
     * @return The message to be displayed.
     */
    public String showSameDateTask(ArrayList<Task> sameDateTasks) {
        if (sameDateTasks.isEmpty()) {
            return "YAYYY no tasks!!";
        } else {
            StringBuilder sb = new StringBuilder("Got it. Tasks on that date:\n");
            for (int i = 0; i < sameDateTasks.size(); i++) {
                sb.append(sameDateTasks.get(i).toString()).append("\n");
            }
            return sb.toString();
        }
    }
}
