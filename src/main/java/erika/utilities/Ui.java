package erika.utilities;

import erika.entities.Task;
import erika.exceptions.ErikaException;

import java.util.Scanner;

/** A class representing a user interface that handles interaction with user */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /** Prints greeting message for the user */
    public void showGreeting() {
        String message = """
                Erika: Hello! I'm Erika
                       What can I do for you?
                       
                       If you are not familiar with me, type "help".
                       I will be glad to fully introduce myself
                """;
        System.out.println(message);
    }

    /** Prints farewell message for the user */
    public void showFarewell() {
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
    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Erika: List is empty");
        } else {
            System.out.println("Erika: Here are the tasks in your list: ");
            tasks.display();
        }
        System.out.println(); // To create line break
    }

    /** Prints the task being marked or unmarked */
    public void showMarkedTask(Task task) {
        String status = task.isDone() ? "done" : "not done yet";
        System.out.println("Erika: Nice! I have marked this task as "
                + status
                + ": \n"
                + "\t "
                + task.toString()
                + "\n");
    }

    /** Prints the task being added to the list */
    public void showAddedTask(Task task) {
        System.out.println("Erika: Got it. I have added this task:"
                + "\n"
                + "\t "
                + (task != null ? task.toString() : "")
                + "\n");
    }

    /** Prints the task being deleted from the list */
    public void showDeletedTask(Task task) {
        System.out.println("Erika: Noted. I have removed this task:"
                + "\n"
                + "\t "
                + task.toString()
                + "\n");
    }

    /** Prints the help message */
    public void showHelp() {
        String helpMessage = """
                Erika: Lets get to know me. My commands are simple:
                    1. help => display list of commands
                    2. list => display list of tasks
                    3. todo <description> => add todo task
                    4. deadline <description> /by dd-MM-yyyy HH:mm => add task with deadline
                    5. event <description> /from dd-MM-yyyy HH:mm /to dd-MM-yyyy HH:mm => add event
                    6. mark <task_number> => mark a task as done
                    7. unmark <task_number> => mark a task as not done
                    8. delete <task_number> => delete a task
                    9. bye => end session
                """;
        System.out.println(helpMessage);
    }

    /** Prints the error message */
    public void showErrorMessage(ErikaException e) {
        System.out.println("Erika: Hmm something went wrong. Please look at the message below:");
        System.out.println(e.getMessage());
    }
}
