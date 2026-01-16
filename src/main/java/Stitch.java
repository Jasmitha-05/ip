import java.util.Scanner;

public class Stitch {
    public static void main(String[] args) {
        String chatBotName = "Stitch";
        Scanner scanner = new Scanner(System.in);

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

            } else {
                System.out.println("     _________________________________");
                System.out.println("     " + userInput);
                System.out.println("     _________________________________");
            }
        }

        scanner.close();
    }
}
