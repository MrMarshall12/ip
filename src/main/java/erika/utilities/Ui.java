package erika.utilities;

import java.util.Scanner;
import java.util.function.Predicate;

import erika.entities.Task;
import erika.exceptions.ErikaException;

/**
 * A class representing a user interface that handles interaction with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Instantiates an instance of Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a greeting message for the user and returns it for GUI purpose.
     */
    public String showGreeting() {
        String message = """
               Hello! I'm Erika
               What can I do for you?
               \s
               If you are not familiar with me, type "help".
               I will be glad to fully introduce myself
               \s""";
        System.out.println(message);
        return message;
    }

    /**
     * Prints a farewell message for the user and returns it for GUI purpose.
     */
    public String showFarewell() {
        String message = """
                Bye. Hope to see you again soon!
                """;
        System.out.println(message);
        return message;
    }

    /**
     * Returns string inputted by the user.
     */
    public String readUserInput() {
        System.out.print("User: ");
        String message = scanner.nextLine();
        System.out.println(); // To create a line break
        return message;
    }

    /**
     * Displays items in the list and returns it for GUI purpose.
     */
    public String showTasks(TaskList tasks) {
        String message = "List is empty";
        String listOfTasks = "";
        if (tasks.isEmpty()) {
            System.out.println(message);
        } else {
            message = "Here are the tasks in your list: ";
            System.out.println(message);
            listOfTasks = tasks.display();
        }
        System.out.println(); // To create a line break
        return message + "\n" + listOfTasks + "\n";
    }

    /**
     * Displays selected items in the list and returns it for GUI purpose.
     */
    public String showSelectedTasks(TaskList tasks, Predicate<Task> criteria) {
        String message = "List is empty";
        String listOfTasks = "";
        if (tasks.isEmpty()) {
            System.out.println(message);
        } else {
            message = "Here are the tasks in your list: ";
            System.out.println(message);
            listOfTasks = tasks.display(criteria);
        }
        System.out.println(); // To create a line break
        return message + "\n" + listOfTasks + "\n";
    }

    /**
     * Prints the task being marked or unmarked and returns it for GUI purpose.
     */
    public String showMarkedTask(Task task) {
        String status = task.isDone() ? "done" : "not done yet";
        String message = "Nice! I have marked this task as "
                + status
                + ": \n"
                + "\t "
                + task.toString()
                + "\n";
        System.out.println(message);
        return message;
    }

    /**
     * Prints the task being added to the list and returns it for GUI purpose.
     */
    public String showAddedTask(Task task) {
        String message = "Got it. I have added this task:"
                + "\n"
                + "\t "
                + (task != null ? task.toString() : "")
                + "\n";
        System.out.println(message);
        return message;
    }

    /**
     * Prints the task being deleted from the list and returns it for GUI purpose.
     */
    public String showDeletedTask(Task task) {
        String message = "Noted. I have removed this task:"
                + "\n"
                + "\t "
                + task.toString()
                + "\n";
        System.out.println(message);
        return message;
    }

    /**
     * Prints the task whose priority is updated from the list and returns it for GUI purpose.
     */
    public String showUpdatedPriorityTask(Task task) {
        String message = "Got it. I have updated the priority this task:"
                + "\n"
                + "\t "
                + task.toString()
                + "\n";
        System.out.println(message);
        return message;
    }

    /**
     * Prints the help message and returns it for GUI purpose.
     */
    public String showHelp() {
        String helpMessage = """
                Lets get to know me. My commands are simple:
                    1. help => display list of commands
                    2. list => display list of tasks
                    3. todo <description> /p <priority> => add todo task
                       priority option:
                        h = high, m = medium, l = low, n = no priority
                        priority is optional, default is no priority.
                    4. deadline <description> /by dd-MM-yyyy HH:mm /p <priority>
                        => add task with deadline
                        priority option:
                        h = high, m = medium, l = low, n = no priority
                        priority is optional, default is no priority.
                    5. event <description> /from dd-MM-yyyy HH:mm /to dd-MM-yyyy HH:mm /p <priority>
                        => add event
                        priority option:
                        h = high, m = medium, l = low, n = no priority
                        priority is optional, default is no priority.
                    6. mark <task_number> => mark a task as done
                    7. unmark <task_number> => mark a task as not done
                    8. delete <task_number> => delete a task
                    9. prio <task_number> <priority> => change priority of a task
                   10. bye => end session
                """;
        System.out.println(helpMessage);
        return helpMessage;
    }

    /**
     * Prints the error message and returns it for GUI purpose.
     */
    public String showErrorMessage(ErikaException e) {
        String message = "Hmm something went wrong. Please look at the message below:"
                + "\n"
                + e.getMessage();
        System.out.println(message);
        return message;
    }

    /**
     * Prints an initialization error message and returns it for GUI purpose.
     * It is static because InitializationError occurs before an instance of Ui is created.
     */
    public static String showInitializationErrorMessage(ErikaException e) {
        String errorMessage = e.getMessage() + "\n";
        String message = errorMessage + """
                   I can't work without my database. Please fix it first. If you can find ErikaDatabase.txt
                   under data folder, please clear its content and ensure the cursor is at line 1 column 1.
                   Also, please remove any empty line.
                   \s
                   See yaa :)
                   \s""";
        System.out.println(message);
        return message;
    }
}
