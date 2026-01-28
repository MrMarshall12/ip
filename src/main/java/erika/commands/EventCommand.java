package erika.commands;

import erika.entities.Events;
import erika.entities.Task;
import erika.exceptions.EmptyDescriptionException;
import erika.exceptions.EmptyStartEndException;
import erika.exceptions.ErikaIOException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class representing an event command.
 */
public class EventCommand extends Command {
    /**
     * Instantiates an instance of EventCommand.
     */
    public EventCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isEvent() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyDescriptionException,
            EmptyStartEndException, ErikaIOException, DateTimeParseException {
        String eventContent = super.formattedMessage.toLowerCase().replace("event", "").strip();
        String[] splitMessage = eventContent.split("/from ");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        String[] splitAroundFrom = super.formattedMessage.split("/from ");
        String[] splitAroundTo = splitAroundFrom.length > 1
                ? splitAroundFrom[1].split("/to")
                : new String[0];
        if (splitAroundTo.length != 2) {
            throw new EmptyStartEndException();
        }

        String startDate = splitAroundTo[0].strip();
        String endDate = splitAroundTo[1].strip();
        Task task = new Events(taskName,
                LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        taskList.add(task);
        ui.showAddedTask(task);
    }

    @Override
    public String toString() {
        return "event";
    }
}
