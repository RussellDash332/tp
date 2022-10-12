package jeryl.fyp.logic.commands;

import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STATUS;

import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.StudentId;

/**
 * Marks the status of a FYP as "DONE", "IP" or "YTS"
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the status of the FYP project done"
            + "by the student ID given in the command "
            + "Existing statuses will be updated by the input.\n"
            + "Parameters: STUDENT_ID (should be in format  \"A\" + (7 digits) + (1 letter), e.g. A0123456G"
            + "s/ STATUS\n"
            + "Example: " + COMMAND_WORD + " A0123456G "
            + PREFIX_STATUS + "DONE";

    public static final String MESSAGE_ARGUMENTS = "studentId: %1$s, Status: %2$s";

    /**
     * Stores all possible statuses as an enumeration.
     */
    public enum Status {
        YTS,
        IP,
        DONE
    }

    private final StudentId studentId;
    private final Status status;

    /**
     * @param studentId of the student doing a particular FYP project
     * @param status of the FYP project
     */
    public MarkCommand(StudentId studentId, Status status) {
        requireAllNonNull(studentId, status);
        this.studentId = studentId;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, studentId, status));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return studentId.equals(e.studentId)
                && status.equals(e.status);
    }
}
