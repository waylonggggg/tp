package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;
import seedu.address.model.person.Person;

/**
 * Removes a CCA from a student identified using it's displayed index from the address book.
 */
public class RemoveCcaFromStudentCommand extends Command {

    public static final String COMMAND_WORD = "remove_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a CCA from the student identified "
            + "by the index number used in the displayed student list.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_CCA_NAME + "CCA_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CCA_NAME + "Basketball";

    public static final String MESSAGE_REMOVE_CCA_SUCCESS = "Removed CCA %2$s from student: %1$s";

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

        if (studentIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToRemoveCca = lastShownPersonList.get(studentIndex.getZeroBased());

        Optional<Cca> searchedCca = findCcaWithCcaName(lastCcaList, ccaName);
        if (searchedCca.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }
        Cca targetCca = searchedCca.get();

        if (!personToRemoveCca.hasCca(targetCca)) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_IN_PERSON);
        }

        Person personWithRemovedCca = personToRemoveCca.removeCca(targetCca);

        try {
            model.setPerson(personToRemoveCca, personWithRemovedCca);
        } catch (IllegalArgumentException e) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_CCA_SUCCESS,
                Messages.format(personWithRemovedCca),
                Messages.format(ccaName)));
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
