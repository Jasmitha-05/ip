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
            }
            
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    DisplayAllTasks(tasks, numOfTasks);
                } else if (userInput.startsWith("mark")) {
                    numOfTasks = MarkTask(tasks, userInput, numOfTasks);
                } else if (userInput.startsWith("unmark")) {
                    numOfTasks = UnmarkTask(tasks, userInput, numOfTasks);
                } else if (userInput.startsWith("todo")) {
                    numOfTasks = ToDoTask(tasks, userInput, numOfTasks);
                } else if (userInput.startsWith("deadline")) {
                    numOfTasks = DeadlineTask(tasks, userInput, numOfTasks);
                } else if (userInput.startsWith("event")) {
                    numOfTasks = EventTask(tasks, userInput, numOfTasks);
                } else {
                    throw new StitchException("I'm sorry, I don't understand.");
                }
            } catch (StitchException e) {
                System.out.println("     ______________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("     ______________________________");

                }
            }

         scanner.close();
    }

        private static void DisplayAllTasks(Task[] tasks, int numOfTasks) {
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

        private static int MarkTask(Task[] tasks, String userInput, int numOfTasks) throws StitchException {
            if (numOfTasks == 0) {
                    throw new StitchException("OOPS! no more task to mark.");
                } else {
                    int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[order].markAsDone();

                    System.out.println("     ______________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("     " + tasks[order].toString());
                    System.out.println("     ______________________________");
                }
            return numOfTasks;
        }

        private static int UnmarkTask(Task[] tasks, String userInput, int numOfTasks) throws StitchException {
            if (numOfTasks == 0) {
                    throw new StitchException("OOPS! no more task to unmark.");
                } else {
                    int order = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[order].markAsUnDone();

                    System.out.println("     ______________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("     " + tasks[order].toString());
                    System.out.println("     ______________________________");
                }
                return numOfTasks;
        }

        private static int ToDoTask(Task[] tasks, String userInput, int numOfTasks) throws StitchException {
            String removeToDOString = userInput.replaceFirst("todo", "").trim();

            if (removeToDOString.isEmpty()) {
                throw new StitchException("OOPS! you forgot to name the todo task");
            }

            Task newTask = new ToDo(removeToDOString); 
            tasks[numOfTasks] = newTask;
            numOfTasks++;
            System.out.println("     ______________________________");
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + newTask.toString());
            System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
            System.out.println("     ______________________________");
            return numOfTasks;
        }

        private static int DeadlineTask(Task[] tasks, String userInput, int numOfTasks) throws StitchException {
            String removeToDOString = userInput.replaceFirst("deadline ", "");
            String[] splitInput = removeToDOString.split(" /by");

            if(splitInput.length < 2 || splitInput[0].trim().isEmpty() || splitInput[1].trim().isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the task name or time?");

            }

            Task newTask = new Deadline(splitInput[0], splitInput[1]); 
            tasks[numOfTasks] = newTask;
            numOfTasks++;
            System.out.println("     ______________________________");
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + newTask.toString());
            System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
            System.out.println("     ______________________________");
            return numOfTasks;
        }

        private static int EventTask(Task[] tasks, String userInput, int numOfTasks) throws StitchException {
            String removeToDOString = userInput.replaceFirst("event ", "");
                    String[] splitInput = removeToDOString.split(" /from| /to");

            if(splitInput.length < 3 || splitInput[0].trim().isEmpty() || splitInput[1].trim().isEmpty() || splitInput[2].trim().isEmpty()) {
                throw new StitchException("OOPS! did you forget to add the task name or timing?");

            }    

                    Task newTask = new Event(splitInput[0], splitInput[1], splitInput[2]); 
                    tasks[numOfTasks] = newTask;
                    numOfTasks++;
                    System.out.println("     ______________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("     " + newTask.toString());
                    System.out.println("     Now you have " + (numOfTasks) + " tasks in the list.");
                    System.out.println("     ______________________________");
                    return numOfTasks;
        }
    }