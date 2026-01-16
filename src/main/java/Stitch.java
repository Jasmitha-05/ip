import java.util.Scanner;

public class Stitch {
    public static void main(String[] args) {
        String chatBotName = "Stitch";
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int numOfTasks = 0;

        System.out.println("     _________________________________");
        System.out.println("     Hello! I'm " + chatBotName);
        System.out.println("     What can I do for you?");
        System.out.println("     _________________________________");

        while(true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("     _________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("     _________________________________");
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("     _________________________________");
                if (numOfTasks == 0) {
                    System.out.println("     Good job! You have no more tasks.");
                } else {
                    int order = 1;
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.println("     " + order + ". " + tasks[i]);
                        order++;
                    }
                }
                System.out.println("     _________________________________");
            }

            else {
                    tasks[numOfTasks] = userInput;
                    numOfTasks++;
                    System.out.println("     _________________________________");
                    System.out.println("     added: " + userInput);
                    System.out.println("     _________________________________");
                }
            }

             scanner.close();
        }
 
    }
