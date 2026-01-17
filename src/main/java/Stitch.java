import java.util.Scanner;

public class Stitch {
    public static void main(String[] args) {
        String chatBotName = "Stitch";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numOfTasks = 0;

        System.out.println("     ______________________________"); //Greet
        System.out.println("     Hello! I'm " + chatBotName);
        System.out.println("     What can I do for you?");
        System.out.println("     ______________________________");

        while(true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) { //Exit
                System.out.println("     ______________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("     ______________________________");
                break;

            } else if (userInput.equalsIgnoreCase("list")) { //List tasks
                System.out.println("     ______________________________");
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
                System.out.println("     ______________________________");
            }

            else if (userInput.startsWith("mark ")) { //Mark task as done
                if (numOfTasks == 0) {
                    System.out.println("     ______________________________");
                    System.out.println("     You have no tasks to mark as done.");
                    System.out.println("     ______________________________");
                } else {
                    int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[order].markAsDone();

                    System.out.println("     ______________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("     " + tasks[order].toString());
                    System.out.println("     ______________________________");
                }
            }

            else if (userInput.startsWith("unmark ")) { //Unmark task 
                if (numOfTasks == 0) {
                    System.out.println("     ______________________________");
                    System.out.println("     You have no tasks to unmark.");
                    System.out.println("     ______________________________");
                } else {
                    int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[order].markAsUnDone();

                    System.out.println("     ______________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("     " + tasks[order].toString());
                    System.out.println("     ______________________________");
                }
            }

            else if (userInput.startsWith("todo ")) { //Add new todo task
                    String removeToDOString = userInput.replaceFirst("todo ", "");
                    Task newTask = new ToDo(removeToDOString); 
                    tasks[numOfTasks] = newTask;
                    numOfTasks++;
                    System.out.println("     ______________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("     " + newTask.toString());
                    System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
                    System.out.println("     ______________________________");
                }
            
                else if (userInput.startsWith("deadline ")) { //Add new deadline task
                    String removeToDOString = userInput.replaceFirst("deadline ", "");
                    String[] splitInput = removeToDOString.split(" /by");
                    Task newTask = new Deadline(splitInput[0], splitInput[1]); 
                    tasks[numOfTasks] = newTask;
                    numOfTasks++;
                    System.out.println("     ______________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("     " + newTask.toString());
                    System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
                    System.out.println("     ______________________________");
                }

                else if (userInput.startsWith("event ")) { //Add new event task
                    String removeToDOString = userInput.replaceFirst("event ", "");
                    String[] splitInput = removeToDOString.split(" /from| /to");
                    Task newTask = new Event(splitInput[0], splitInput[1], splitInput[2]); 
                    tasks[numOfTasks] = newTask;
                    numOfTasks++;
                    System.out.println("     ______________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("     " + newTask.toString());
                    System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
                    System.out.println("     ______________________________");
                }

                else {
                    System.out.println("     ______________________________");
                    System.out.println("     I'm sorry, I don't understand.");
                    System.out.println("     ______________________________");
                }
            }

             scanner.close();
        }
 
    }
