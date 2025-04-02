// --- Revised File: seedu.address.logic.commands.RemoveCcaFromStudentCommand.java ---
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
// Ensure this is the correct CommandException import
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;
import seedu.address.model.person.Person;
// No CcaNotFoundException import needed here

/**
 * Removes a CCA from a student identified using it's displayed index from the address book.
 */
public class RemoveCcaFromStudentCommand extends Command {

    public static final String COMMAND_WORD = "remove_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a CCA from the student identified "
            + "by the index number used in the displayed student list.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_CCA + "CCA_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CCA + "Basketball";

    public static final String MESSAGE_REMOVE_CCA_SUCCESS = "Removed CCA %2$s from student: %1$s";
    // Using existing Messages.MESSAGE_CCA_NOT_IN_PERSON
    // Using existing Messages.MESSAGE_CCA_NOT_FOUND for CCA not in system

    private final Index studentIndex;
    private final CcaName ccaName;

    /**
     * @param studentIndex of the student in the filtered student list to remove CCA from.
     * @param ccaName of the CCA to remove.
     */
    public RemoveCcaFromStudentCommand(Index studentIndex, CcaName ccaName) {
        requireAllNonNull(studentIndex, ccaName);
        this.studentIndex = studentIndex;
        this.ccaName = ccaName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Cca> lastCcaList = model.getCcaList();

        // 1. Validate Student Index
        if (studentIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToRemoveCca = lastShownPersonList.get(studentIndex.getZeroBased());

        // 2. Find the CCA in the system's CCA list
        Optional<Cca> searchedCca = findCcaWithCcaName(lastCcaList, ccaName);
        if (searchedCca.isEmpty()) {
            // CCA does not exist in the address book's list of CCAs
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }
        Cca targetCca = searchedCca.get();

        // 3. Check if student actually has this CCA assigned to them
        if (!personToRemoveCca.hasCca(targetCca)) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_IN_PERSON);
        }

        // 4. Create the updated person by removing the CCA
        // Person.removeCca returns a *new* Person object.
        // No try-catch needed around this call based on Person implementation.
        Person personWithRemovedCca = personToRemoveCca.removeCca(targetCca);

        // 5. Update the model
        // Follow the pattern from DeleteRoleFromStudentCommand for the try-catch around setPerson
        try {
            model.setPerson(personToRemoveCca, personWithRemovedCca);
        } catch (IllegalArgumentException e) {
            // Map IllegalArgumentException from setPerson to MESSAGE_CCA_NOT_FOUND
            // as done in the example DeleteRoleFromStudentCommand.
            // Consider if this mapping is truly appropriate for all potential
            // IllegalArgumentExceptions from setPerson. If setPerson guarantees
            // the only IAE is due to CCA validation, this is fine. Otherwise,
            // a more generic error might be better. Sticking to the pattern for now.
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }

        // 6. Update filtered list and return result
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_CCA_SUCCESS,
                Messages.format(personWithRemovedCca), // Use Messages.format for Person
                Messages.format(ccaName)));             // Use Messages.format for CcaName
    }

    /**
     * Finds a {@code Cca} from a list of {@code Cca} with the given {@code CcaName}.
     * Iterative approach matching DeleteRoleFromStudentCommand.
     *
     * @param ccaList list of {@code Cca} to search from
     * @param ccaName {@code CcaName} to search for
     * @return {@code Optional<Cca>} containing the {@code Cca} if found, otherwise {@code Optional.empty()}
     */
    private Optional<Cca> findCcaWithCcaName(List<Cca> ccaList, CcaName ccaName) {
        for (Cca cca : ccaList) {
            if (cca.getCcaName().equals(ccaName)) {
                return Optional.of(cca);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RemoveCcaFromStudentCommand)) {
            return false;
        }
        RemoveCcaFromStudentCommand otherCommand = (RemoveCcaFromStudentCommand) other;
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