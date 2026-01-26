package erika.commands;

import erika.utilities.TaskList;
import erika.utilities.Ui;

/** A class representing a help command */
public class HelpCommand extends Command {
    public HelpCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isHelp() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showHelp();
    }

    @Override
    public String toString() {
        return "help";
    }
}
