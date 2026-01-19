import java.util.Scanner;

/** A class representing the chatbot named Erika */
public class Erika {
    private List list;
    private Scanner scanner;

    public Erika() {
        list = new List();
        scanner = new Scanner(System.in);
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
        System.out.print("User: ");
        String message = scanner.nextLine();
        System.out.println("\n"); // To create line break
        return message;
    }

    /** Displays items in the list */
    public void displayList() {
        System.out.println("Erika: Here are the tasks in your list: ");
        list.display();
        System.out.println(); // To create line break
    }

    /** Marks the status of a task in the list */
    public void markTask(String formattedMessage) {
        String[] splitMessage = formattedMessage.split(" ");
        boolean mark = splitMessage[0].equalsIgnoreCase("mark");
        int index = Integer.parseInt(splitMessage[1]);
        list.mark(index - 1, mark);

        String status = mark ? "done" : "not done yet";
        System.out.println("Nice! I have marked this task as "
                + status
                + ": \n"
                + "\t "
                + list.getTask(index - 1)
                + "\n");
    }

    /** Adds task to the list */
    public void addTask(String formattedMessage) {
        Task task = null;
        if (formattedMessage.toLowerCase().startsWith("todo")) {
            String taskName = formattedMessage.toLowerCase().replace("todo ", "");
            task = new ToDos(taskName);
        } else if (formattedMessage.toLowerCase().startsWith("deadline")) {
            String deadlineContent = formattedMessage.toLowerCase().replace("deadline ", "");
            String[] splitMessage = deadlineContent.split(" /by ");
            String taskName = splitMessage[0];
            String deadlineTime = formattedMessage.split(" /by ")[1];
            task = new Deadlines(taskName, deadlineTime);
        } else if (formattedMessage.toLowerCase().startsWith("event")) {
            String eventContent = formattedMessage.toLowerCase().replace("event ", "");
            String[] splitMessage = eventContent.split(" /from ");
            String eventName = splitMessage[0];
            splitMessage = formattedMessage.split(" /from ")[1].split(" /to ");
            String startDate = splitMessage[0];
            String endDate = splitMessage[1];
            task = new Events(eventName, startDate, endDate);
        }


        list.add(task);

        System.out.println("Got it. I have added this task:"
                + "\n"
                + "\t "
                + (task != null ? task.toString() : "")
                + "\n");

    }

    /** Check if the user input is a list command */
    public boolean isListCommand(String formattedMessage) {
        return formattedMessage.equalsIgnoreCase("list");
    }

    /** Check if the user input is a marking command */
    public boolean isMarkingCommand(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("mark")
                || formattedMessage.toLowerCase().startsWith("unmark");
    }

    /** Check if the user input is an add task command */
    public boolean isAddTaskCommand(String formattedMessage) {
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.startsWith("todo")
                || lowerCase.startsWith("deadline")
                || lowerCase.startsWith("event");
    }

    /** Prints any message passed through the parameter */
    public void respondToUser(String message) {
        String formattedMessage = message.strip();
        if (isListCommand(formattedMessage)) {
            displayList();
        } else if (isMarkingCommand(formattedMessage)) {
            markTask(formattedMessage);
        } else if (isAddTaskCommand(formattedMessage)) {
            addTask(formattedMessage);
        } else {
            System.out.println( "Erika: Hmm, sorry. Please use either todo, deadline, event, or list."
                    + "\n");
        }
    }

    /** Maintain conversation until the user inputs "bye" */
    public void converse() {
        greetUser();

        while (true) {
            String message = readUserInput();
            if (message.equalsIgnoreCase("bye")) {
                break;
            }
            respondToUser(message);
        }

        bidFarewell();
    }

    public static void main(String[] args) {
        Erika erika = new Erika();
        erika.converse();
    }
}
