package seedu.address.testutil;

import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 * This builder corresponds to the {@link EditPersonDescriptor} used by {@link EditStudentCommand},
 * which only handles Name, Phone, Email, and Address fields.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    /**
     * Creates a new {@code EditPersonDescriptorBuilder} with an empty descriptor.
     */
    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    /**
     * Creates a new {@code EditPersonDescriptorBuilder} by copying an existing {@code EditPersonDescriptor}.
     *
     * @param descriptor The descriptor to copy values from.
     */
    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        // Uses EditPersonDescriptor's copy constructor
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Creates a new {@code EditPersonDescriptorBuilder} initialized with the basic details
     * (Name, Phone, Email, Address) of the given {@code person}.
     * CCA information from the person is ignored.
     *
     * @param person The person whose details to use.
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     *
     * @param name The name to set.
     * @return This builder instance.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     *
     * @param phone The phone number to set.
     * @return This builder instance.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     *
     * @param email The email to set.
     * @return This builder instance.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     *
     * @param address The address to set.
     * @return This builder instance.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Builds and returns the configured {@code EditPersonDescriptor}.
     *
     * @return The built EditPersonDescriptor object.
     */
    public EditPersonDescriptor build() {
        return descriptor;
    }
}
