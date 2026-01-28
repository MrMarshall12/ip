package erika.commands;

import erika.entities.Deadlines;
import erika.entities.Task;
import erika.exceptions.EmptyDeadlineException;
import erika.exceptions.EmptyDescriptionException;
import erika.exceptions.ErikaDateTimeParseException;
import erika.exceptions.ErikaIOException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a deadline command.
 */
public class DeadlineCommand extends Command {
    /**
     * Instantiates an instance of DeadlineCommand.
     */
    public DeadlineCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isDeadline() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyDescriptionException,
            EmptyDeadlineException, ErikaIOException, ErikaDateTimeParseException {
        String deadlineContent = super.formattedMessage.toLowerCase().replace("deadline", "").strip();
        String[] splitMessage = deadlineContent.split("/by");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        String[] splitAroundBy = super.formattedMessage.split("/by");
        String deadlineTime = splitAroundBy.length == 2
                ? splitAroundBy[1].strip()
                : "";
        if (deadlineTime.isEmpty()) {
            throw new EmptyDeadlineException();
        }

        Task task = new Deadlines(taskName, LocalDateTime.parse(deadlineTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        taskList.add(task);
        ui.showAddedTask(task);
    }

    @Override
    public String toString() {
        return "deadline";
    }
}
