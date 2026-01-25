import erika.entities.Deadlines;
import erika.entities.Events;
import erika.entities.Task;
import erika.entities.ToDos;
import erika.exceptions.EmptyDeadlineException;
import erika.exceptions.EmptyDescriptionException;
import erika.exceptions.EmptyStartEndException;
import erika.exceptions.ErikaException;
import erika.exceptions.InvalidDeleteCommandException;
import erika.exceptions.InvalidMarkCommandException;
import erika.exceptions.OutOfBoundsException;
import erika.utilities.List;

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
    private void greetUser() {
        String message = """
                Erika: Hello! I'm Erika
                       What can I do for you?
                """;
        System.out.println(message);
    }

    /** Prints farewell message for the user */
    private void bidFarewell() {
        String message = """
                Erika: Bye. Hope to see you again soon!
                """;
        System.out.println(message);
    }

    /** Returns string inputted by the user */
    private String readUserInput() {
        System.out.print("User: ");
        String message = scanner.nextLine();
        System.out.println(); // To create line break
        return message;
    }

    /** Displays items in the list */
    private void displayList() {
        if (list.isEmpty()) {
            System.out.println("Erika: erika.utilities.List is empty");
        } else {
            System.out.println("Erika: Here are the tasks in your list: ");
            list.display();
        }
        System.out.println(); // To create line break
    }

    /** Marks the status of a task in the list */
    private void markTask(String formattedMessage) throws InvalidMarkCommandException,
            OutOfBoundsException {
        String[] splitMessage = formattedMessage.split(" ");
        if (splitMessage.length != 2) {
            throw new InvalidMarkCommandException();
        }
        boolean mark = splitMessage[0].equalsIgnoreCase("mark");
        int index = -1;
        try {
            index = Integer.parseInt(splitMessage[1]);
        } catch (NumberFormatException e) {
            throw new InvalidMarkCommandException();
        }

        if (!list.isWithinBounds(index - 1)) {
            throw new OutOfBoundsException();
        }

        list.mark(index - 1, mark);

        String status = mark ? "done" : "not done yet";
        System.out.println("Erika: Nice! I have marked this task as "
                + status
                + ": \n"
                + "\t "
                + list.getTask(index - 1)
                + "\n");
    }

    /** Checks if the add command is a todo */
    private boolean isToDo(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("todo");
    }

    /** Checks if the add command is a deadline */
    private boolean isDeadline(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("deadline");
    }

    /** Checks if the add command is an event */
    private boolean isEvent(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("event");
    }

    /** Adds todo task to the list */
    private Task addTodo(String formattedMessage) throws EmptyDescriptionException {
        String taskName = formattedMessage.toLowerCase().replace("todo", "").strip();
        if  (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new ToDos(taskName);
        list.add(task);
        return task;
    }

    /** Adds deadline task to the list */
    private Task addDeadline(String formattedMessage) throws EmptyDescriptionException,
            EmptyDeadlineException {
        String deadlineContent = formattedMessage.toLowerCase().replace("deadline", "").strip();
        String[] splitMessage = deadlineContent.split("/by");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if (taskName.isEmpty()) {
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
    private Task addEvent(String formattedMessage) throws EmptyDescriptionException,
            EmptyStartEndException {
        String eventContent = formattedMessage.toLowerCase().replace("event", "").strip();
        String[] splitMessage = eventContent.split("/from ");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if (taskName.isEmpty()) {
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
    private void addTask(String formattedMessage) throws EmptyDescriptionException,
            EmptyDeadlineException, EmptyStartEndException {
        Task task = null;
        if (isToDo(formattedMessage)) {
            task = addTodo(formattedMessage);
        } else if (isDeadline(formattedMessage)) {
            task = addDeadline(formattedMessage);
        } else if (isEvent(formattedMessage)) {
            task = addEvent(formattedMessage);
        }

        System.out.println("Erika: Got it. I have added this task:"
                + "\n"
                + "\t "
                + (task != null ? task.toString() : "")
                + "\n");

    }

    /** Deletes task from the list */
    private void deleteTask(String formattedMessage) throws InvalidDeleteCommandException,
            OutOfBoundsException{
        String[] splitMessage = formattedMessage.split(" ");
        if (splitMessage.length != 2) {
            throw new InvalidDeleteCommandException();
        }
        boolean mark = splitMessage[0].equalsIgnoreCase("delete");
        int index = -1;
        try {
            index = Integer.parseInt(splitMessage[1]);
        } catch (NumberFormatException e) {
            throw new InvalidDeleteCommandException();
        }

        if (!list.isWithinBounds(index - 1)) {
            throw new OutOfBoundsException();
        }

        Task task = list.remove(index - 1);

        System.out.println("Erika: Noted. I have removed this task:"
                + "\n"
                + "\t "
                + task.toString()
                + "\n");
    }

    /** Checks if the user input is a list command */
    private boolean isListCommand(String formattedMessage) {
        return formattedMessage.equalsIgnoreCase("list");
    }

    /** Checks if the user input is a marking command */
    private boolean isMarkingCommand(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("mark")
                || formattedMessage.toLowerCase().startsWith("unmark");
    }

    /** Checks if the user input is an add task command */
    private boolean isAddTaskCommand(String formattedMessage) {
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.startsWith("todo")
                || lowerCase.startsWith("deadline")
                || lowerCase.startsWith("event");
    }

    /** Checks if the user input is a delete task command */
    private boolean isDeleteTaskCommand(String formattedMessage) {
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.startsWith("delete");
    }

    /** Prints any message passed through the parameter */
    private void respondToUser(String message) {
        String formattedMessage = message.strip();
        try {
            if (isListCommand(formattedMessage)) {
                displayList();
            } else if (isMarkingCommand(formattedMessage)) {
                markTask(formattedMessage);
            } else if (isAddTaskCommand(formattedMessage)) {
                addTask(formattedMessage);
            } else if (isDeleteTaskCommand(formattedMessage)) {
                deleteTask(formattedMessage);
            } else {
                System.out.println("Erika: Hmm, sorry. Please use either todo, deadline, event, mark, unmark, "
                        + "or list command."
                        + "\n");
            }
        } catch (ErikaException e) {
            System.out.println("Erika: Hmm something went wrong. Please look at the message below:");
            System.out.println(e.getMessage());
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
