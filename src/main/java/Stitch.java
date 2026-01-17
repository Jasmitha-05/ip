import java.util.Scanner;

public class Stitch {
    public static void main(String[] args) {
        String chatBotName = "Stitch";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numOfTasks = 0;

        System.out.println("     _________________________________"); //Greet
        System.out.println("     Hello! I'm " + chatBotName);
        System.out.println("     What can I do for you?");
        System.out.println("     _________________________________");

        while(true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) { //Exit
                System.out.println("     _________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("     _________________________________");
                break;

            } else if (userInput.equalsIgnoreCase("list")) { //List tasks
                System.out.println("     _________________________________");
                if (numOfTasks == 0) {
                    System.out.println("     Good job! You have no more tasks.");
                } else {
                    System.out.println("     Here are the tasks in your list:");
                    int order = 1;
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.println("     " + order + ". " + tasks[i].toString());
                        order++;
                    }
                }
                System.out.println("     _________________________________");
            }

            else if (userInput.startsWith("mark ")) { //Mark task as done
                if (numOfTasks == 0) {
                    System.out.println("     _________________________________");
                    System.out.println("     You have no tasks to mark as done.");
                    System.out.println("     _________________________________");
                } else {
                    int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[order].markAsDone();

                    System.out.println("     _________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("     " + tasks[order].toString());
                    System.out.println("________________________________");
                }
            }

            else if (userInput.startsWith("unmark ")) { //Unmark task 
                if (numOfTasks == 0) {
                    System.out.println("     _________________________________");
                    System.out.println("     You have no tasks to unmark.");
                    System.out.println("     _________________________________");
                } else {
                    int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[order].markAsUnDone();

                    System.out.println("     _________________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("     " + tasks[order].toString());
                    System.out.println("________________________________");
                }
            }

            else {
                    tasks[numOfTasks] = new Task(userInput); //Add new task
                    numOfTasks++;
                    System.out.println("     _________________________________");
                    System.out.println("     added: " + userInput);
                    System.out.println("     _________________________________");
                }
            }

             scanner.close();
        }
 
    }
