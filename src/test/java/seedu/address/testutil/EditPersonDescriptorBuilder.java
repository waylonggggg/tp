package seedu.address.testutil;

// Removed unused CCA/Role related imports if they are no longer needed by other methods
// import seedu.address.model.cca.Attendance;
// import seedu.address.model.cca.Cca;
// import seedu.address.model.cca.CcaInformation;
// import seedu.address.model.cca.CcaName;
// import seedu.address.model.cca.SessionCount;
// import seedu.address.model.role.Role;
// import java.util.HashSet;
// import java.util.Set;


import seedu.address.logic.commands.EditStudentCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;


/**
 * A utility class to help with building EditPersonDescriptor objects.
 * This builder corresponds to the EditPersonDescriptor used by EditStudentCommand,
 * which only handles Name, Phone, Email, and Address.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        // Uses EditPersonDescriptor's copy constructor, which should already be updated
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     * for Name, Phone, Email, and Address only. CCA information is ignored.
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        // REMOVED: descriptor.setCcaInformation(person.getCcaInformations());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    // METHOD REMOVED: withCcaInformation(String ccaName, String roleName, int totalSessions)
    // This method is no longer valid as EditPersonDescriptor does not store CCA info.
    /*
    public EditPersonDescriptorBuilder withCcaInformation(String ccaName, String roleName, int totalSessions) {
        // ... implementation removed ...
        // descriptor.setCcaInformation(ccaInfoSet); // This line is no longer valid
        return this;
    }
    */

    public EditPersonDescriptor build() {
        return descriptor;
    }
}