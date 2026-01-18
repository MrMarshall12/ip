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

    /** Displays items in the list */
    public void displayList() {
        System.out.println("\n" + "Erika: Here are the tasks in your list: ");
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
        System.out.println("\n" + "Nice! I have marked this task as "
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

        if (task == null) {
            System.out.println( "\n"
                    + "Erika: Sorry, I can't understand you. Please try again."
                    + "\n");
        } else {
            list.add(task);

            System.out.println("\n" + "Got it. I have added this task:"
                    + "\n"
                    + "\t "
                    + (task != null ? task.toString() : "")
                    + "\n");
        }
    }

    /** Prints any message passed through the parameter */
    public void respondToUser(String message) {
        String formattedMessage = message.strip();
        if (formattedMessage.equalsIgnoreCase("list")) {
            displayList();
        } else if (formattedMessage.toLowerCase().startsWith("mark")
                || formattedMessage.toLowerCase().startsWith("unmark")) {
            markTask(formattedMessage);
        } else {
            addTask(formattedMessage);
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
