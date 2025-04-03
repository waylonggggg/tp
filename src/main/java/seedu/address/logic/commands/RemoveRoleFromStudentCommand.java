package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA_NAME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;
import seedu.address.model.person.Person;

/**
 * Removes a role and sets to a default role of a specified cca from a student identified
 * using it's displayed index from the address book.
 */
public class RemoveRoleFromStudentCommand extends Command {

    public static final String COMMAND_WORD = "remove_r";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a role from the student identified "
            + "by the index number used in the displayed student list. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CCA_NAME + "CCA_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CCA_NAME + "Basketball";

    public static final String MESSAGE_REMOVE_ROLE_FROM_STUDENT_SUCCESS = "removed role from student: %1$s";
    public static final String MESSAGE_ROLE_NOT_ASSIGNED = "This student does not have a role in this CCA.";

    private final Index studentIndex;
    private final CcaName ccaName;

    /**
     * @param studentIndex of the student in the filtered student list to remove role
     * @param ccaName of the CCA to remove role
     */
    public RemoveRoleFromStudentCommand(Index studentIndex, CcaName ccaName) {
        requireAllNonNull(studentIndex, ccaName);
        this.studentIndex = studentIndex;
        this.ccaName = ccaName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (studentIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToRemoveRole = lastShownPersonList.get(studentIndex.getZeroBased());
        if (!model.hasCca(ccaName)) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }

        Cca targetCca = model.getCca(ccaName);
        if (!personToRemoveRole.hasCca(targetCca)) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_IN_PERSON);
        }
        if (personToRemoveRole.isDefaultRoleInCca(targetCca)) {
            throw new CommandException(MESSAGE_ROLE_NOT_ASSIGNED);
        }

        Person personWithRemovedRole = personToRemoveRole.removeRole(targetCca);
        try {
            model.setPerson(personToRemoveRole, personWithRemovedRole);
        } catch (IllegalArgumentException e) {
            throw new CommandException(Messages.MESSAGE_CCA_NOT_FOUND);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_ROLE_FROM_STUDENT_SUCCESS, personWithRemovedRole));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RemoveRoleFromStudentCommand)) {
            return false;
        }

        RemoveRoleFromStudentCommand otherCommand = (RemoveRoleFromStudentCommand) other;
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
