package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaInformation;
import seedu.address.model.cca.CcaName;
import seedu.address.model.person.Person;
import seedu.address.model.role.Role;

/**
 * Adds a CCA to a student identified using their displayed index from the address book.
 * The CCA must exist in the address book's CCA list, and the student must not already be enrolled in it.
 * A default role and attendance record (based on the CCA's total sessions) will be assigned.
 */
public class AddCcaToStudentCommand extends Command {

    /** The command word for adding a CCA to a student. */
    public static final String COMMAND_WORD = "add_c";

    /** Usage message for the add_c command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a CCA to the student identified "
            + "by the index number used in the displayed student list.\n"
            + "The CCA must already exist in the CCA list.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_CCA_NAME + "CCA_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CCA_NAME + "Basketball";

    /** Success message displayed upon successful execution. */
    public static final String MESSAGE_ADD_CCA_SUCCESS = "Added CCA %2$s to student: %1$s";
    /** Error message displayed if the student is already enrolled in the CCA. */
    public static final String MESSAGE_CCA_ALREADY_PRESENT = "This student is already enrolled in this CCA.";

    private final Index studentIndex;
    private final CcaName ccaName;

    /**
     * Creates an AddCcaToStudentCommand to add the specified {@code CcaName}
     *   to the student at the {@code studentIndex}.
     *
     * @param studentIndex index of the student in the filtered person list to add CCA to. Cannot be null.
     * @param ccaName name of the CCA to add. Cannot be null.
     */
    public AddCcaToStudentCommand(Index studentIndex, CcaName ccaName) {
        requireAllNonNull(studentIndex, ccaName);
        this.studentIndex = studentIndex;
        this.ccaName = ccaName;
    }

    /**
     * Executes the command to add the specified CCA to the student.
     * Finds the student and the CCA, checks for validity (index bounds, CCA existence, non-duplication),
     * creates a new Person object with the added CCA information, updates the model, and returns a success message.
     *
     * @param model {@code Model} which the command should operate on. Cannot be null.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution (e.g., invalid index,
     *     CCA not found, student already has CCA).
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        // Check 1: Validate Student Index
        if (studentIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.hasCca(ccaName)) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }

        Person personToAddCca = lastShownPersonList.get(studentIndex.getZeroBased());
        assert personToAddCca != null : "Person object should exist at the validated index.";

        // Check 2: Find the target CCA in the model's CCA list
        Cca targetCca = model.getCca(ccaName);
        assert targetCca != null : "Target Cca object should exist in the model.";

        if (personToAddCca.hasCca(targetCca)) {
            throw new CommandException(MESSAGE_CCA_ALREADY_PRESENT);
        }

        CcaInformation newCcaInfo = new CcaInformation(targetCca,
                Role.DEFAULT_ROLE,
                targetCca.createNewAttendance());

        Set<CcaInformation> updatedCcaInformations = new HashSet<>(personToAddCca.getCcaInformations());
        updatedCcaInformations.add(newCcaInfo);

        Person personWithAddedCca = personToAddCca.addCca(newCcaInfo);

        assert personWithAddedCca != null : "Newly created Person object should not be null.";
        assert personWithAddedCca.hasCca(targetCca) : "Newly created Person should have the target CCA.";

        model.setPerson(personToAddCca, personWithAddedCca);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_CCA_SUCCESS,
                Messages.format(personWithAddedCca),
                Messages.format(ccaName)));
    }

    /**
     * Checks if this command is equal to another object.
     * Equality is based on the student index and the CCA name.
     *
     * @param other The object to compare against.
     * @return true if the objects are the same or have the same index and CCA name, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCcaToStudentCommand)) {
            return false;
        }

        AddCcaToStudentCommand otherCommand = (AddCcaToStudentCommand) other;
        assert otherCommand != null : "Object should be an instance of AddCcaToStudentCommand.";
        return studentIndex.equals(otherCommand.studentIndex)
                && ccaName.equals(otherCommand.ccaName);
    }

    /**
     * Returns a string representation of the command, including the index and CCA name.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentIndex", studentIndex)
                .add("ccaName", ccaName)
                .toString();
    }
}
