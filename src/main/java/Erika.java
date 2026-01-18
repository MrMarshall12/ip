import java.util.Scanner;

/** A class representing the chatbot named Erika */
public class Erika {
    private List list;

    public Erika() {
        list = new List();
    }
    /** Prints greeting message for the user */
    public void greetUser() {
        String message = """
                Erika: Hello! I'm Erika
                       What can I do for you?
                """;
        System.out.println(message);
    }

    /** Prints farewell message for the user */
    public void bidFarewell() {
        String message = """
                Erika: Bye. Hope to see you again.
                """;
        System.out.println(message);
    }

    /** Returns string inputted by the user */
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("User: ");
        return scanner.nextLine();
    }

    /** Prints any message passed through the parameter */
    public void respondToUser(String message) {
        if (message.strip().equalsIgnoreCase("list")) {
            list.display();
        } else {
            list.add(message);
        }
    }

    /** Maintain conversation until the user inputs "bye" */
    public void converse() {
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
        Erika erika = new Erika();
        erika.converse();
    }
}
