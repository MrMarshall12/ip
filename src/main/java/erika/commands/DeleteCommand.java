package erika.commands;

import erika.entities.Task;
import erika.exceptions.ErikaIOException;
import erika.exceptions.InvalidDeleteCommandException;
import erika.exceptions.OutOfBoundsException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

/** A class representing a delete command */
public class DeleteCommand extends Command {
    public DeleteCommand(String formattedMessage)  {
        super(formattedMessage);
    }

    @Override
    public boolean isDelete() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidDeleteCommandException,
            OutOfBoundsException, ErikaIOException {
        String[] splitMessage = super.formattedMessage.split(" ");
        if (splitMessage.length != 2) {
            throw new InvalidDeleteCommandException();
        }
        int index = -1;
        try {
            index = Integer.parseInt(splitMessage[1]);
        } catch (NumberFormatException e) {
            throw new InvalidDeleteCommandException();
        }

        if (!taskList.isWithinBounds(index - 1)) {
            throw new OutOfBoundsException();
        }

        Task task = taskList.remove(index - 1);
        ui.showDeletedTask(task);
    }
}
