package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaInformation;
import seedu.address.model.cca.CcaName;
import seedu.address.model.cca.SessionCount;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.role.Role;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Cca[] getSampleCcas() {
        return new Cca[] {
                new Cca(new CcaName("Acting"), new HashSet<>(), new SessionCount(10)),
                new Cca(new CcaName("Basketball"), new HashSet<>(), new SessionCount(12)),
                new Cca(new CcaName("Badminton"), new HashSet<>(), new SessionCount(15)),
                new Cca(new CcaName("Canoe"), new HashSet<>(), new SessionCount(8)),
                new Cca(new CcaName("Dance"), new HashSet<>(), new SessionCount(20)),
                new Cca(new CcaName("E Sports"), new HashSet<>(), new SessionCount(5)),
                new Cca(new CcaName("Football"), new HashSet<>(), new SessionCount(14))
        };
    }

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getRoleSet("friends"), getCcaSet("Acting")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getRoleSet("colleagues", "friends"), getCcaSet("Basketball", "Badminton")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getRoleSet("neighbours"),
                    getCcaSet("Canoe")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getRoleSet("family"), getCcaSet("Dance")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getRoleSet("classmates"), getCcaSet("E Sports")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getRoleSet("colleagues"), getCcaSet("Football"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Cca sampleCca : getSampleCcas()) {
            sampleAb.addCca(sampleCca);
        }
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static Set<CcaInformation> getCcaInformationSet(List<Cca> uniqueCcaList, String... ccaInformationData) throws IllegalValueException {
        if (strings.length % 4 != 0) {
            throw new IllegalArgumentException("Arguments should be in sets of 4: (ccaName, role, attendedSessions, totalSessions)");
        }

        Set<CcaInformation> ccaInformationSet = new HashSet<>();

        for (int i = 0; i < strings.length / 4; i += 4) {
            String ccaName = strings[i];
            String role =
        }
    }

    /**
     * Returns a role set containing the list of strings given.
     */
    public static Set<Role> getRoleSet(String... strings) {
        return Arrays.stream(strings)
                .map(Role::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a cca set containing the list of strings given.
     */
    public static Set<Cca> getCcaSet(String... strings) {
        return Arrays.stream(strings)
                .map(CcaName::new)
                .map(Cca::new)
                .collect(Collectors.toSet());
    }

}
