package erika.commands;

import erika.utilities.TaskList;
import erika.utilities.Ui;

public class FindCommand extends Command {
    public FindCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isFind() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showTasks(taskList);
    }

    @Override
    public String toString() {
        return "find";
    }
}
