package ben.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ben.core.command.ByeCommand;
import ben.core.command.Command;
import ben.core.command.DeadlineCommand;
import ben.core.command.DeleteCommand;
import ben.core.command.EventCommand;
import ben.core.command.FindCommand;
import ben.core.command.FixedTaskCommand;
import ben.core.command.ListCommand;
import ben.core.command.MarkCommand;
import ben.core.command.TodoCommand;
import ben.core.command.UnmarkCommand;
import ben.exception.BenDatetimeParseException;
import ben.exception.BenEmptyParameterValueException;
import ben.exception.BenInvalidCommandException;
import ben.exception.BenInvalidParameterException;
import ben.exception.BenMissingParameterException;

/**
 * The class that handles the user input parsing functionality.
 */
public class Parser {
    private static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns a ByeCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A ByeCommand object with the data given.
     */
    private static ByeCommand parseByeCommand(String[] commandParameters) {
        return new ByeCommand();
    }

    /**
     * Returns a ListCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A ListCommand object with the data given.
     */
    private static ListCommand parseListCommand(String[] commandParameters) {
        return new ListCommand();
    }

    /**
     * Returns a MarkCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A MarkCommand object with the data given.
     */
    private static MarkCommand parseMarkCommand(String[] commandParameters) throws BenInvalidParameterException {
        try {
            // Obtain indexNumber
            int indexNumber = Integer.parseInt(commandParameters[1]);

            return new MarkCommand(indexNumber);
        } catch (NumberFormatException e) {
            throw new BenInvalidParameterException(commandParameters[1]);
        }
    }

    /**
     * Returns a UnmarkCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A UnmarkCommand object with the data given.
     */
    private static UnmarkCommand parseUnmarkCommand(String[] commandParameters) throws BenInvalidParameterException {
        try {
            // Obtain indexNumber
            int indexNumber = Integer.parseInt(commandParameters[1]);

            return new UnmarkCommand(indexNumber);
        } catch (NumberFormatException e) {
            throw new BenInvalidParameterException(commandParameters[1]);
        }
    }

    /**
     * Returns a TodoCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A TodoCommand object with the data given.
     */
    private static TodoCommand parseTodoCommand(String[] commandParameters) throws BenEmptyParameterValueException {
        int commandParametersLength = commandParameters.length;

        // Throw an exception if there is no description
        if (commandParametersLength <= 1) {
            throw new BenEmptyParameterValueException("description", "todo");
        }

        // Obtain the description of the Todo task
        StringBuilder todoDescriptionBuilder = new StringBuilder(commandParameters[1]);
        for (int i = 2; i < commandParametersLength; i++) {
            todoDescriptionBuilder.append(" ");
            todoDescriptionBuilder.append(commandParameters[i]);
        }
        String todoDescription = todoDescriptionBuilder.toString();

        return new TodoCommand(todoDescription);
    }

    /**
     * Returns a DeadlineCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A DeadlineCommand object with the data given.
     */
    private static DeadlineCommand parseDeadlineCommand(String[] commandParameters)
            throws BenEmptyParameterValueException,
            BenMissingParameterException {
        int commandParametersLength = commandParameters.length;

        // Throw an exception if there is no description
        if (commandParametersLength <= 1) {
            throw new BenEmptyParameterValueException("description", "deadline");
        }

        Integer byIndex = null;

        // Obtain the description of the Deadline task
        StringBuilder deadlineDescriptionBuilder = new StringBuilder(commandParameters[1]);
        for (int i = 2; i < commandParametersLength; i++) {
            // Stop once "/by" is encountered
            if (commandParameters[i].equals("/by")) {
                byIndex = i;
                break;
            }

            deadlineDescriptionBuilder.append(" ");
            deadlineDescriptionBuilder.append(commandParameters[i]);
        }
        String deadlineDescription = deadlineDescriptionBuilder.toString();

        // Throw an exception if there is no "/by"
        if (byIndex == null) {
            throw new BenMissingParameterException("/by");
        }

        // Throw an exception if there is no value for "/by"
        if (byIndex + 1 >= commandParametersLength) {
            throw new BenEmptyParameterValueException("/by", "deadline");
        }

        // Obtain the deadline of the Deadline task
        StringBuilder deadlineByBuilder = new StringBuilder(commandParameters[byIndex + 1]);
        for (int i = byIndex + 2; i < commandParametersLength; i++) {
            deadlineByBuilder.append(" ");
            deadlineByBuilder.append(commandParameters[i]);
        }
        String deadlineBy = deadlineByBuilder.toString();

        // Convert deadlineBy to LocalDatetime
        LocalDateTime datetimeBy = LocalDateTime.parse(deadlineBy, DATETIME_FORMATTER);

        return new DeadlineCommand(deadlineDescription, datetimeBy);
    }

    /**
     * Returns a FixedTaskCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A FixedTaskCommand object with the data given.
     */
    private static FixedTaskCommand parseFixedTaskCommand(String[] commandParameters)
            throws BenEmptyParameterValueException,
            BenMissingParameterException {
        int commandParametersLength = commandParameters.length;

        // Throw an exception if there is no description
        if (commandParametersLength <= 1) {
            throw new BenEmptyParameterValueException("description", "fixed task");
        }

        Integer byIndex = null;

        // Obtain the description of the Fixed Task
        StringBuilder fixedTaskDescriptionBuilder = new StringBuilder(commandParameters[1]);
        for (int i = 2; i < commandParametersLength; i++) {
            // Stop once "/for" is encountered
            if (commandParameters[i].equals("/for")) {
                byIndex = i;
                break;
            }

            fixedTaskDescriptionBuilder.append(" ");
            fixedTaskDescriptionBuilder.append(commandParameters[i]);
        }
        String deadlineDescription = fixedTaskDescriptionBuilder.toString();

        // Throw an exception if there is no "/for"
        if (byIndex == null) {
            throw new BenMissingParameterException("/for");
        }

        // Throw an exception if there is no value for "/for"
        if (byIndex + 1 >= commandParametersLength) {
            throw new BenEmptyParameterValueException("/for", "fixed task");
        }

        // Obtain the deadline of the Fixed task
        StringBuilder fixedTaskForBuilder = new StringBuilder(commandParameters[byIndex + 1]);
        for (int i = byIndex + 2; i < commandParametersLength; i++) {
            fixedTaskForBuilder.append(" ");
            fixedTaskForBuilder.append(commandParameters[i]);
        }
        String fixedTaskFor = fixedTaskForBuilder.toString();

        return new FixedTaskCommand(deadlineDescription, fixedTaskFor);
    }

    /**
     * Returns a EventCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A EventCommand object with the data given.
     */
    private static EventCommand parseEventCommand(String[] commandParameters)
            throws BenEmptyParameterValueException,
            BenMissingParameterException {
        int commandParametersLength = commandParameters.length;

        // Throw an exception if there is no description
        if (commandParametersLength <= 1) {
            throw new BenEmptyParameterValueException("description", "event");
        }

        Integer fromIndex = null;
        Integer toIndex = null;

        // Obtain the description of the Event task
        StringBuilder eventDescriptionBuilder = new StringBuilder(commandParameters[1]);
        for (int i = 2; i < commandParametersLength; i++) {
            // Stop once "/from" is encountered
            if (commandParameters[i].equals("/from")) {
                fromIndex = i;
                break;
            }

            eventDescriptionBuilder.append(" ");
            eventDescriptionBuilder.append(commandParameters[i]);
        }
        String eventDescription = eventDescriptionBuilder.toString();

        // Throw an exception if there is no "/from"
        if (fromIndex == null) {
            throw new BenMissingParameterException("/from");
        }

        // Throw an exception if there is no value for "/from"
        if (fromIndex + 1 >= commandParametersLength
                || commandParameters[fromIndex + 1].equals("/to")) {
            throw new BenEmptyParameterValueException("/from", "event");
        }

        // Obtain the to date/time of the Event task
        StringBuilder eventFromBuilder = new StringBuilder(commandParameters[fromIndex + 1]);
        for (int i = fromIndex + 2; i < commandParametersLength; i++) {
            // Stop once "/end" is encountered
            if (commandParameters[i].equals("/to")) {
                toIndex = i;
                break;
            }

            eventFromBuilder.append(" ");
            eventFromBuilder.append(commandParameters[i]);
        }
        String eventFrom = eventFromBuilder.toString();

        // Convert eventFrom to LocalDateTime
        LocalDateTime dateTimeFrom = LocalDateTime.parse(eventFrom, DATETIME_FORMATTER);

        // Throw an exception if there is no "/to"
        if (toIndex == null) {
            throw new BenMissingParameterException("/to");
        }

        // Throw an exception if there is no value for "/to"
        if (toIndex + 1 >= commandParametersLength) {
            throw new BenEmptyParameterValueException("/to", "event");
        }

        // Obtain the end date/time of the Event task
        StringBuilder eventToBuilder = new StringBuilder(commandParameters[toIndex + 1]);
        for (int i = toIndex + 2; i < commandParametersLength; i++) {
            eventToBuilder.append(" ");
            eventToBuilder.append(commandParameters[i]);
        }
        String eventTo = eventToBuilder.toString();

        // Convert eventTo to LocalDateTime
        LocalDateTime dateTimeTo = LocalDateTime.parse(eventTo, DATETIME_FORMATTER);

        return new EventCommand(eventDescription, dateTimeFrom, dateTimeTo);
    }

    /**
     * Returns a DeleteCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A DeleteCommand object with the data given.
     */
    private static DeleteCommand parseDeleteCommand(String[] commandParameters)
            throws BenInvalidParameterException {
        try {
            // Obtain indexNumber
            int indexNumber = Integer.parseInt(commandParameters[1]);

            return new DeleteCommand(indexNumber);
        } catch (NumberFormatException e) {
            throw new BenInvalidParameterException(commandParameters[1]);
        }
    }

    /**
     * Returns a FindCommand object based on parameters.
     *
     * @param commandParameters the separated parameters of the command.
     * @return A FindCommand object with the data given.
     */
    private static FindCommand parseFindCommand(String[] commandParameters)
            throws BenEmptyParameterValueException {
        int commandParametersLength = commandParameters.length;

        // Throw an exception if there is no query parameter
        if (commandParametersLength <= 1) {
            throw new BenEmptyParameterValueException("find query", "event");
        }

        // Obtain the find query parameter
        StringBuilder findQueryBuilder = new StringBuilder(commandParameters[1]);
        for (int i = 2; i < commandParametersLength; i++) {
            findQueryBuilder.append(" ");
            findQueryBuilder.append(commandParameters[i]);
        }
        String findQuery = findQueryBuilder.toString();

        return new FindCommand(findQuery);
    }

    /**
     * Parses fullCommand and returns the command to be
     * executed in the form of a Command object.
     *
     * @param fullCommand The full input from the user.
     * @return The command to be executed.
     * @throws BenInvalidParameterException If the parameter is not allowed.
     * @throws BenEmptyParameterValueException If no value was specified
     *                                         for a parameter.
     * @throws BenMissingParameterException If no required parameter was specified.
     * @throws BenInvalidCommandException If the command does not exist.
     * @throws BenDatetimeParseException If the datetime is not in the valid format.
     */
    public static Command parse(String fullCommand)
            throws BenInvalidParameterException,
            BenEmptyParameterValueException,
            BenMissingParameterException,
            BenInvalidCommandException,
            BenDatetimeParseException {
        // Split input into main command and parameters
        String[] commandParameters = fullCommand.split(" ");
        int commandParametersLength = commandParameters.length;

        // Get main command
        String command = commandParameters[0];
        try {
            if (command.equals("bye")) {
                return parseByeCommand(commandParameters);
            } else if (command.equals("list")) {
                return parseListCommand(commandParameters);
            } else if (command.equals("mark")) {
                return parseMarkCommand(commandParameters);
            } else if (command.equals("unmark")) {
                return parseUnmarkCommand(commandParameters);
            } else if (command.equals("todo")) {
                return parseTodoCommand(commandParameters);
            } else if (command.equals("fixedtask")) {
                return parseFixedTaskCommand(commandParameters);
            } else if (command.equals("deadline")) {
                return parseDeadlineCommand(commandParameters);
            } else if (command.equals("event")) {
                return parseEventCommand(commandParameters);
            } else if (command.equals("delete")) {
                return parseDeleteCommand(commandParameters);
            } else if (command.equals("find")) {
                return parseFindCommand(commandParameters);
            } else {
                throw new BenInvalidCommandException();
            }
        } catch (DateTimeParseException e) {
            throw new BenDatetimeParseException();
        }
    }
}
