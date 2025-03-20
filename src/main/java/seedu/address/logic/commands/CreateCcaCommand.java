package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cca.Cca;

/**
 * Adds a person to the address book.
 */
public class CreateCcaCommand extends Command {

    public static final String COMMAND_WORD = "create_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a CCA to the address book. "
            + "Parameters: "
            + PREFIX_CCA_NAME + "NAME ";

    public static final String MESSAGE_SUCCESS = "New CCA added: %1$s";
    public static final String MESSAGE_DUPLICATE_CCA = "This CCA already exists in the address book";

    private final Cca toCreate;

    /**
     * Creates a CreateCcaCommand to add the specified {@code CCA}
     */
    public CreateCcaCommand(Cca cca) {
        requireNonNull(cca);
        toCreate = cca;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCca(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_CCA);
        }

        model.addCca(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toCreate)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateCcaCommand)) {
            return false;
        }

        CreateCcaCommand otherCreateCcaCommand = (CreateCcaCommand) other;
        return toCreate.equals(otherCreateCcaCommand.toCreate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreate", toCreate)
                .toString();
    }

    @Override
    public int hashCode() {
        return toCreate.hashCode();
    }
}
