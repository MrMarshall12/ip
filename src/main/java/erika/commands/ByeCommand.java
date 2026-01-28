package erika.commands;

import erika.utilities.TaskList;
import erika.utilities.Ui;

/**
 * A class representing a bye command.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates an instance of ByeCommand.
     */
    public ByeCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showFarewell();
    }

    @Override
    public String toString() {
        return "bye";
    }
}
