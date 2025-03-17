package seedu.address.testutil;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.cca.Cca;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withCca(Basketball).withPerson(John).build();}
 * Make sure withCca is called before withPerson
 * Make sure withCcas is called before withPersons
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Cca} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCca(Cca cca) {
        addressBook.addCca(cca);
        return this;
    }

    /**
     * Adds a list of {@code Cca} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCcas(List<Cca> ccas) {
        for (Cca cca : ccas) {
            addressBook.addCca(cca);
        }
        return this;
    }

    /**
     * Adds a list of {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPersons(List<Person> persons) {
        for (Person person : persons) {
            addressBook.addPerson(person);
        }
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
