package erika.commands;

import erika.utilities.TaskList;
import erika.utilities.Ui;

/**
 * A class representing a list command.
 */
public class ListCommand extends Command {
    /**
     * Instantiates an instance of ListCommand.
     */
    public ListCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showTasks(taskList);
    }

    @Override
    public String toString() {
        return "list";
    }
}
