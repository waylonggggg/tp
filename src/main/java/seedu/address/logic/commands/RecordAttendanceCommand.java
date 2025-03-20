package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA_NAME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaInformation;
import seedu.address.model.cca.CcaName;
import seedu.address.model.person.Person;

/**
 * Records the attendance of a member in a CCA.
 */
public class RecordAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "attend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records the attendance of a member in a CCA. "
            + "Parameters: "
            + "STUDENT_INDEX "
            + PREFIX_CCA_NAME + "CCA_NAME "
            + PREFIX_AMOUNT + "AMOUNT\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CCA_NAME + "Basketball " + PREFIX_AMOUNT + "1";

    public static final String MESSAGE_SUCCESS = "Attendance recorded: %1$s";
    public static final String MESSAGE_CCA_NOT_FOUND = "This student is not in the CCA.";

    private final Index studentIndex;
    private final CcaName ccaName;
    private final int amount;

    /**
     * Creates a RecordAttendanceCommand to record the specified {@code Attendance}
     */
    public RecordAttendanceCommand(Index studentIndex, CcaName ccaName, int amount) {
        requireNonNull(studentIndex);
        requireNonNull(ccaName);
        requireNonNull(amount);
        this.studentIndex = studentIndex;
        this.ccaName = ccaName;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person student = lastShownList.get(studentIndex.getZeroBased());
        if (!student.hasCca(ccaName)) {
            throw new CommandException(MESSAGE_CCA_NOT_FOUND);
        }
        try {
            Cca cca = student.getCca(ccaName);
            CcaInformation ccaInformation = student.getCcaInformation(cca);
            model.recordAttendance(ccaInformation.getCca(), student, amount);
            return new CommandResult(String.format(MESSAGE_SUCCESS, student.getName()
                + " in " + ccaInformation.getCca().getCcaName()));
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        } catch (NullPointerException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_CCA_DISPLAYED_INDEX);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecordAttendanceCommand)) {
            return false;
        }

        RecordAttendanceCommand otherRecordAttendanceCommand = (RecordAttendanceCommand) other;
        return studentIndex.equals(otherRecordAttendanceCommand.studentIndex)
                && ccaName.equals(otherRecordAttendanceCommand.ccaName)
                && amount == otherRecordAttendanceCommand.amount;
    }
}
