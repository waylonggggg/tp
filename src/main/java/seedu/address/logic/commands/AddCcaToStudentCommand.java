// --- New File: seedu.address.logic.commands.AddCcaToStudentCommand.java ---
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import seedu.address.model.role.Role; // Needed for default role

/**
 * Adds a CCA to a student identified using it's displayed index from the address book.
 */
public class AddCcaToStudentCommand extends Command {

    public static final String COMMAND_WORD = "add_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a CCA to the student identified "
            + "by the index number used in the displayed student list.\n"
            + "The CCA must already exist in the CCA list.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_CCA + "CCA_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CCA + "Basketball";

    public static final String MESSAGE_ADD_CCA_SUCCESS = "Added CCA %2$s to student: %1$s";
    public static final String MESSAGE_CCA_ALREADY_PRESENT = "This student is already enrolled in this CCA.";
    // Using existing Messages.MESSAGE_CCA_NOT_FOUND for CCA not in system

    private final Index studentIndex;
    private final CcaName ccaName;

    /**
     * @param studentIndex of the student in the filtered student list to add CCA to.
     * @param ccaName of the CCA to add.
     */
    public AddCcaToStudentCommand(Index studentIndex, CcaName ccaName) {
        requireAllNonNull(studentIndex, ccaName);
        this.studentIndex = studentIndex;
        this.ccaName = ccaName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Cca> lastCcaList = model.getCcaList(); // Use getCcaList() which returns List<Cca>

        // Validate Student Index
        if (studentIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToAddCca = lastShownPersonList.get(studentIndex.getZeroBased());

        // Find the CCA in the system's CCA list
        Optional<Cca> searchedCca = findCcaWithCcaName(lastCcaList, ccaName);
        if (searchedCca.isEmpty()) {
            // Use the specific message for CCA not existing in the address book
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }
        Cca targetCca = searchedCca.get();

        // Check if student already has this CCA
        if (personToAddCca.hasCca(targetCca)) {
            throw new CommandException(MESSAGE_CCA_ALREADY_PRESENT);
        }

        // Create the new CcaInformation for the student
        // Use the default role and create initial attendance based on the CCA's total sessions
        CcaInformation newCcaInfo = new CcaInformation(targetCca,
                new Role(Role.DEFAULT_ROLE_NAME),
                targetCca.createNewAttendance());

        // Create the updated person
        Set<CcaInformation> updatedCcaInformations = new HashSet<>(personToAddCca.getCcaInformations());
        updatedCcaInformations.add(newCcaInfo);

        Person personWithAddedCca = new Person(personToAddCca.getName(), personToAddCca.getPhone(),
                personToAddCca.getEmail(), personToAddCca.getAddress(),
                updatedCcaInformations);

        // Update the model
        // setPerson might throw IllegalArgumentException if the added CCA (targetCca)
        // doesn't exist in the model's internal CCA list during validation.
        // This check is slightly redundant since we found it earlier, but good practice.
        try {
            model.setPerson(personToAddCca, personWithAddedCca);
        } catch (IllegalArgumentException e) {
            // This likely corresponds to MESSAGE_CCA_NOT_FOUND if setPerson checks CCA existence.
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_CCA_SUCCESS,
                Messages.format(personWithAddedCca),
                Messages.format(ccaName)));
    }

    /**
     * Finds a {@code Cca} from a list of {@code Cca} with the given {@code CcaName}.
     *
     * @param ccaList list of {@code Cca} to search from
     * @param ccaName {@code CcaName} to search for
     * @return {@code Optional<Cca>} containing the {@code Cca} if found, otherwise {@code Optional.empty()}
     */
    private Optional<Cca> findCcaWithCcaName(List<Cca> ccaList, CcaName ccaName) {
        return ccaList.stream()
                .filter(cca -> cca.getCcaName().equals(ccaName))
                .findFirst();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddCcaToStudentCommand)) {
            return false;
        }
        AddCcaToStudentCommand otherCommand = (AddCcaToStudentCommand) other;
        return studentIndex.equals(otherCommand.studentIndex)
                && ccaName.equals(otherCommand.ccaName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentIndex", studentIndex)
                .add("ccaName", ccaName)
                .toString();
    }
}
