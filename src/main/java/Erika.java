import java.util.Scanner;

/** A class representing the chatbot named Erika */
public class Erika {
    /** Prints greeting message for the user */
    public static void greetUser() {
        String message = """
                Erika: Hello! I'm Erika
                       What can I do for you?
                """;
        System.out.println(message);
    }

    /** Prints farewell message for the user */
    public static void bidFarewell() {
        String message = """
                Erika: Bye. Hope to see you again.
                """;
        System.out.println(message);
    }

    /** Returns string inputted by the user */
    public static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("User: ");
        return scanner.nextLine();
    }

    /** Prints any message passed through the parameter */
    public static void respondToUser(String message) {
        System.out.println("\n" + "Erika: " + message + "\n");
    }

    /** Maintain conversation until the user inputs "bye" */
    public static void converse() {
        greetUser();

        String message = "";
        while (!message.equals("bye")) {
            message = readUserInput();
            respondToUser(message);
            message = message.toLowerCase().strip();
        }

        bidFarewell();
    }

    public static void main(String[] args) {
        converse();
    }
}
