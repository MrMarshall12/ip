package erika.chatbot;

import erika.commands.Command;
import erika.exceptions.ErikaException;
import erika.exceptions.ErikaIoException;
import erika.utilities.Parser;
import erika.utilities.TaskList;
import erika.utilities.Ui;


/**
 * A class representing the chatbot named Erika.
 */
public class Erika {
    private Command commandType;
    private TaskList list;
    private Ui ui;

    /**
     * Instantiates an instance of Erika.
     */
    public Erika() throws ErikaIoException {
        list = new TaskList();
        ui = new Ui();
    }

    /**
     * Returns greeting message.
     */
    public String getGreeting() {
        return ui.showGreeting();
    }

    /**
     * Maintains conversation until the user inputs "bye".
     */
    public void converse() {
        ui.showGreeting();

        boolean isBye = false;
        while (!isBye) {
            try {
                String command = ui.readUserInput();
                Command commandType = Parser.parseCommand(command);
                commandType.execute(list, ui);
                isBye = commandType.isBye();
            } catch (ErikaException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    /**
     * Responds to user's input passed through GUI.
     * This method is an overload of the converse method designed for GUI.
     */
    public String converse(String command) {
        String message = "";
        try {
            commandType = Parser.parseCommand(command);
            message = commandType.execute(list, ui);
        } catch (ErikaException e) {
            message = ui.showErrorMessage(e);
        }
        return message;
    }

    /**
     * Returns the command type of the current execution.
     */
    public String getCommandType() {
        return commandType.toString();
    }

    /**
     * Provides the main entrance to the program.
     */
    public static void main(String[] args) {
        try {
            Erika erika = new Erika();
            erika.converse();
        } catch (ErikaIoException e) {
            Ui.showInitializationErrorMessage(e);
        }
    }
}
