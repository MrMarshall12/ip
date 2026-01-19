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
        System.out.println(); // To create line break
        return message;
    }

    /** Displays items in the list */
    public void displayList() {
        if (list.isEmpty()) {
            System.out.println("Erika: List is empty");
        } else {
            System.out.println("Erika: Here are the tasks in your list: ");
            list.display();
        }
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

    /** Checks if the add command is a todo */
    public boolean isToDo(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("todo");
    }

    /** Checks if the add command is a deadline */
    public boolean isDeadline(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("deadline");
    }

    /** Checks if the add command is an event */
    public boolean isEvent(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("event");
    }

    /** Adds todo task to the list */
    public Task addTodo(String formattedMessage) throws EmptyDescriptionException {
        String taskName = formattedMessage.toLowerCase().replace("todo", "").strip();
        if  (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new ToDos(taskName);
        list.add(task);
        return task;
    }

    /** Adds deadline task to the list */
    public Task addDeadline(String formattedMessage) throws EmptyDescriptionException,
            EmptyDeadlineException {
        String deadlineContent = formattedMessage.toLowerCase().replace("deadline", "").strip();
        String[] splitMessage = deadlineContent.split("/by");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if  (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        String[] splitAroundBy = formattedMessage.split("/by");
        String deadlineTime = splitAroundBy.length == 2
                ? splitAroundBy[1].strip()
                : "";
        if (deadlineTime.isEmpty()) {
            throw new EmptyDeadlineException();
        }

        Task task = new Deadlines(taskName, deadlineTime);
        list.add(task);
        return task;
    }

    /** Adds event task to the list */
    public Task addEvent(String formattedMessage) throws EmptyDescriptionException,
            EmptyStartEndException {
        String eventContent = formattedMessage.toLowerCase().replace("event", "").strip();
        String[] splitMessage = eventContent.split("/from ");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if  (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        String[] splitAroundFrom = formattedMessage.split("/from ");
        String[] splitAroundTo = splitAroundFrom.length > 1
                ? splitAroundFrom[1].split("/to")
                : new String[0];
        if (splitAroundTo.length != 2) {
            throw new EmptyStartEndException();
        }

        String startDate = splitAroundTo[0].strip();
        String endDate = splitAroundTo[1].strip();
        Task task = new Events(taskName, startDate, endDate);
        list.add(task);
        return task;
    }

    /** Adds task to the list */
    public void addTask(String formattedMessage) throws EmptyDescriptionException,
            EmptyDeadlineException, EmptyStartEndException {
        Task task = null;
        if (isToDo(formattedMessage)) {
            task = addTodo(formattedMessage);
        } else if (isDeadline(formattedMessage)) {
            task = addDeadline(formattedMessage);
        } else if (isEvent(formattedMessage)) {
            task = addEvent(formattedMessage);
        }

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
            try {
                addTask(formattedMessage);
            } catch (ErikaException e) {
                System.out.println("Erika: Hmm something went wrong. Please look at the message below:");
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println( "Erika: Hmm, sorry. Please use either todo, deadline, event, or list command."
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
