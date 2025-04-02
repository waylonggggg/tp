package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

// Removed unused import if Set is no longer needed here
// import java.util.Set;

import seedu.address.logic.commands.CreateStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditPersonDescriptor;
// Removed unused import
// import seedu.address.model.cca.CcaInformation;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns a create student command string for adding the {@code person}.
     * Assumes CreateStudentCommand can handle the generated details string including CCAs/Roles.
     */
    public static String getCreateStudentCommand(Person person) {
        return CreateStudentCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details,
     * including Name, Phone, Email, Address, and potentially CCAs/Roles
     * intended for use with CreateStudentCommand.
     * NOTE: Check if CreateStudentCommand actually handles PREFIX_ROLE.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME).append(person.getName().fullName).append(" ");
        sb.append(PREFIX_PHONE).append(person.getPhone().value).append(" ");
        sb.append(PREFIX_EMAIL).append(person.getEmail().value).append(" ");
        sb.append(PREFIX_ADDRESS).append(person.getAddress().value).append(" ");

        // Include CCA/Role information - assuming CreateStudent handles this format
        person.getCcaInformations().forEach(ccaInfo -> {
            sb.append(PREFIX_CCA).append(ccaInfo.getCca().getCcaName().fullCcaName).append(" ");
            // If CreateStudentCommand does not handle roles with PREFIX_ROLE, this line should be removed
            sb.append(PREFIX_ROLE).append(ccaInfo.getRole().roleName).append(" ");
        });
        // Trim trailing space if any prefixes were added
        return sb.toString().trim();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     * This only includes Name, Phone, Email, and Address details, as EditStudentCommand
     * no longer handles CCAs or Roles.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));

        // REMOVED: Block for handling CcaInformation, as it's no longer in EditPersonDescriptor
        /*
        if (descriptor.getCcaInformation().isPresent()) {
            // ... logic removed ...
        }
        */
        // Trim trailing space if any prefixes were added
        return sb.toString().trim();
    }
}