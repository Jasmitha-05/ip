package stitch;

import java.util.ArrayList;

/**
 * Represents the displaying of UI message to display to users.
 */
public class Ui {

    /**
     * Ui message to greet user when chatbot is first started.
     * 
     * @param chatBotName Name of the chatbot is Stitch
     */
    public void showGreet(String chatBotName) {
        System.out.println("     ______________________________"); // Greet
        System.out.println("     Hello! I'm " + chatBotName);
        System.out.println("     What can I do for you?");
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to bid farewell when user wants to exit.
     */
    public void showBye() {
        System.out.println("     ______________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to display all error messages to user.
     * 
     * @param message Error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println("     ______________________________");
        System.out.println("     " + message);
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to display the current task being added and the total number of
     * tasks in the list
     * 
     * @param task       The task being added.
     * @param numOfTasks The total number of tasks in the list.
     */
    public void showAddTask(Task task, int numOfTasks) {
        System.out.println("     ______________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to display the current task being deleted and the total number of
     * tasks in the list
     * 
     * @param task       The task being deleted.
     * @param numOfTasks The total number of tasks in the list.
     */
    public void showDeleteTask(Task task, int numOfTasks) {
        System.out.println("     ______________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to display the current task being marked as done
     * 
     * @param task The task being marked as done.
     */
    public void showMarkTask(Task task) {
        System.out.println("     ______________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + task.toString());
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to display the current task being unmarked undone.
     * 
     * @param task The task being unmarked undone.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("     ______________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("     " + task.toString());
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to display all tasks in the list.
     * 
     * @param tasks      The ArrayList of tasks in the list.
     * @param numOfTasks The total number of tasks in the list.
     */
    public void showAllTask(ArrayList<Task> tasks, int numOfTasks) {
        System.out.println("     ______________________________");
        if (numOfTasks == 0) {
            System.out.println("     Good job! You have no more tasks.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            int order = 1;
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + order + ". " + tasks.get(i).toString());
                order++;
            }
        }
        System.out.println("     ______________________________");
    }

    /**
     * Ui message to display all matching tasks with the given keyword by user.
     * 
     * @param matches list of tasks containing all tasks with the matching keyword.
     */
    public void showFindTask(ArrayList<Task> matches) {
        System.out.println("     ______________________________");
        if (matches.isEmpty()) {
            System.out.println("     No matching task and keywords!!");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + matches.get(i).toString());
            }
        }
        System.out.println("     ______________________________");
    }
}
