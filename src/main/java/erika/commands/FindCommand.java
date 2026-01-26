package erika.commands;

import erika.entities.Task;
import erika.exceptions.ErikaIOException;
import erika.exceptions.InvalidMarkCommandException;
import erika.exceptions.OutOfBoundsException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

import java.util.function.Predicate;

public class FindCommand extends Command {
    public FindCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isFind() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidMarkCommandException,
            OutOfBoundsException, ErikaIOException {
        String target = super.formattedMessage.replace("find", "").strip();
        Predicate<Task> predicate = t -> t.getTaskName().toLowerCase().startsWith(target);

        ui.showSelectedTasks(taskList, predicate);
    }

    @Override
    public String toString() {
        return "find";
    }
}
