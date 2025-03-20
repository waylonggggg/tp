package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaInformation;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<CcaInformation> ccaInformation;

    /**
     * Constructs a {@code Person}.
     * Ensures all fields are non-null and initializes with a defensive copy of the provided set.
     *
     * @param name The person's name.
     * @param phone The person's phone number.
     * @param email The person's email address.
     * @param address The person's address.
     * @param ccaInformation The set of CCA-related information associated with the person.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<CcaInformation> ccaInformation) {
        requireAllNonNull(name, phone, email, address, ccaInformation);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.ccaInformation = new HashSet<>(ccaInformation);
    }

    /**
     * Returns the person's name.
     *
     * @return A {@code Name} object representing the person's name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the person's phone number.
     *
     * @return A {@code Phone} object representing the person's phone number.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns the person's email address.
     *
     * @return An {@code Email} object representing the person's email address.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the person's address.
     *
     * @return An {@code Address} object representing the person's address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns a set of CCAs associated with this person.
     * Extracts the Cca objects from the CcaInformation set.
     *
     * @return An unmodifiable list of Cca objects.
     */
    public Set<Cca> getCcas() {
        return ccaInformation.stream()
                .map(CcaInformation::getCca) // Extracts the Cca object from CcaInformation
                .collect(Collectors.toSet()); // Returns an immutable set
    }

    /**
     * Returns an unmodifiable view of the person's CCA information.
     * Prevents external modifications to maintain immutability.
     *
     * @return An unmodifiable {@code Set<CcaInformation>} associated with the person.
     */
    public Set<CcaInformation> getCcaInformation() {
        return Collections.unmodifiableSet(ccaInformation);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson The person to compare against.
     * @return {@code true} if both persons have the same name, otherwise {@code false}.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Checks if two persons are equal based on all identity and data fields.
     * Defines a stronger notion of equality.
     *
     * @param other The object to compare against.
     * @return {@code true} if both persons have the same details, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && ccaInformation.equals(otherPerson.ccaInformation);
    }

    /**
     * Generates a hash code for this person based on all identity and data fields.
     *
     * @return A hash code value for this person.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, ccaInformation);
    }

    /**
     * Returns a string representation of the person.
     *
     * @return A formatted string containing the person's details.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("ccainformation", ccaInformation)
                .toString();
    }

}
