package stitch;

import java.util.ArrayList;

public class Ui {
    public void showGreet(String chatBotName) {
        System.out.println("     ______________________________"); // Greet
        System.out.println("     Hello! I'm " + chatBotName);
        System.out.println("     What can I do for you?");
        System.out.println("     ______________________________");
    }

    public void showBye() {
        System.out.println("     ______________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("     ______________________________");
    }

    public void showErrorMessage(String message) {
        System.out.println("     ______________________________");
        System.out.println("     " + message);
        System.out.println("     ______________________________");
    }

    public void showAddTask(Task task, int numOfTasks) {
        System.out.println("     ______________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
        System.out.println("     ______________________________");
    }

    public void showDeleteTask(Task task, int numOfTasks) {
        System.out.println("     ______________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
        System.out.println("     ______________________________");
    }

    public void showMarkTask(Task task) {
        System.out.println("     ______________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + task.toString());
        System.out.println("     ______________________________");
    }

    public void showUnmarkTask(Task task) {
        System.out.println("     ______________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("     " + task.toString());
        System.out.println("     ______________________________");
    }

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
}
