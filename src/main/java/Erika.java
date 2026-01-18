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
                Erika: Bye. Hope to see you again soon!
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
        String formattedMessage = message.strip().toLowerCase();
        if (formattedMessage.equals("list")) {
            System.out.println("\n" + "Erika: Here are the tasks in your list: ");
            list.display();
            System.out.println(); // To create line break
        } else if (formattedMessage.startsWith("mark") || formattedMessage.startsWith("unmark")) {
            String[] splitMessage = formattedMessage.split(" ");
            boolean mark = splitMessage[0].equals("mark");
            int index = Integer.parseInt(splitMessage[1]);
            list.mark(index - 1, mark);

            String status = mark ? "done" : "not done yet";
            System.out.println("\n" + "Nice! I have marked this task as "
                    + status
                    + ": \n"
                    + "\t "
                    + list.getTask(index - 1)
                    + "\n");
        } else {
            String response = list.add(message);
            System.out.println("\n" + "Erika: I have added "
                    + response + " to the list" + "\n");
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
