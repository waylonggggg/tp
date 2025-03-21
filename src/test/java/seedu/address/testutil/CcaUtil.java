package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA_NAME;

import seedu.address.logic.commands.CreateCcaCommand;
import seedu.address.model.cca.Cca;

/**
 * A utility class for Cca.
 */
public class CcaUtil {

    /**
     * Returns a create cca command string for adding the {@code cca}.
     */
    public static String getCreateCcaCommand(Cca cca) {
        return CreateCcaCommand.COMMAND_WORD + " " + getCcaDetails(cca);
    }

    /**
     * Returns the part of command string for the given {@code cca}'s details.
     */
    public static String getCcaDetails(Cca cca) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CCA_NAME + cca.getCcaName().fullCcaName + " ");
        return sb.toString();
    }
}
