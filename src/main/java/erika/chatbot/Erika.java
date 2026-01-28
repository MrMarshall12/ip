package erika.chatbot;

import erika.commands.Command;
import erika.exceptions.ErikaException;
import erika.exceptions.ErikaIOException;
import erika.utilities.Parser;
import erika.utilities.TaskList;
import erika.utilities.Ui;


/**
 * A class representing the chatbot named Erika.
 */
public class Erika {
    private TaskList list;
    private Ui ui;

    /**
     * Instantiates an instance of Erika.
     */
    public Erika() throws ErikaIOException {
        list = new TaskList();
        ui = new Ui();
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
     * Provides main entrance to the program.
     */
    public static void main(String[] args) {
        try {
            Erika erika = new Erika();
            erika.converse();
        } catch (ErikaIOException e) {
            Ui.showInitializationErrorMessage(e);
        }
    }
}
