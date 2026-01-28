package erika.commands;

import erika.exceptions.ErikaIOException;
import erika.exceptions.InvalidMarkCommandException;
import erika.exceptions.OutOfBoundsException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

/**
 * A class representing a mark command.
 */
public class MarkCommand extends Command {
    /**
     * Instantiates an instance of MarkCommand.
     */
    public MarkCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isMark() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidMarkCommandException,
            OutOfBoundsException, ErikaIOException {
        String[] splitMessage = super.formattedMessage.split(" ");
        if (splitMessage.length != 2) {
            throw new InvalidMarkCommandException();
        }
        boolean mark = splitMessage[0].equalsIgnoreCase("mark");
        int index = -1;
        try {
            index = Integer.parseInt(splitMessage[1]);
        } catch (NumberFormatException e) {
            throw new InvalidMarkCommandException();
        }

        if (!taskList.isWithinBounds(index - 1)) {
            throw new OutOfBoundsException();
        }

        taskList.mark(index - 1, mark);
        ui.showMarkedTask(taskList.getTask(index - 1));
    }

    @Override
    public String toString() {
        return "mark";
    }
}
