import java.util.Scanner;

/** A class representing the chatbot named Erika */
public class Erika {
    /** Prints greeting message for the user */
    public static void greetUser() {
        String message = """
                Hello! I'm Erika
                What can I do for you?
                """;
        System.out.println(message);
    }

    /** Prints farewell message for the user */
    public static void bidFarewell() {
        String message = """
                Bye. Hope to see you again.
                """;
        System.out.println(message);
    }

    /** Returns string inputted by the user */
    public static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /** Prints any message passed through the parameter */
    public static void respondToUser(String message) {
        System.out.println("\n" + message);
    }

    public static void main(String[] args) {
        greetUser();
        respondToUser(readUserInput());
        bidFarewell();
    }
}
