package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalCcas.ACTING;
import static seedu.address.testutil.TypicalCcas.BADMINTON;
import static seedu.address.testutil.TypicalCcas.BASKETBALL;
import static seedu.address.testutil.TypicalCcas.GARDENING;
import static seedu.address.testutil.TypicalCcas.SWIMMING;
import static seedu.address.testutil.TypicalCcas.TABLE_TENNIS;
import static seedu.address.testutil.TypicalCcas.TENNIS;
import static seedu.address.testutil.TypicalCcas.TRACK_AND_FIELD;
import static seedu.address.testutil.TypicalCcas.VOLLEYBALL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaInformation;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final CcaInformation ALICE_ACTING = new CcaInformationBuilder().withCca(ACTING)
            .withRole("Vice-President").withAttendance(8).build();
    public static final CcaInformation ALICE_BASKETBALL = new CcaInformationBuilder().withCca(BASKETBALL)
            .withRole("Member").withAttendance(10).build();
    public static final CcaInformation BENSON_BADMINTON = new CcaInformationBuilder().withCca(BADMINTON)
            .withRole("Member").withAttendance(7).build();
    public static final CcaInformation BENSON_SWIMMING = new CcaInformationBuilder().withCca(SWIMMING)
            .withRole("Captain").withAttendance(9).build();
    public static final CcaInformation CARL_TABLE_TENNIS = new CcaInformationBuilder().withCca(TABLE_TENNIS)
            .withRole("Captain").withAttendance(12).build();
    public static final CcaInformation CARL_TENNIS = new CcaInformationBuilder().withCca(TENNIS)
            .withRole("Member").withAttendance(17).build();
    public static final CcaInformation DANIEL_TRACK_AND_FIELD = new CcaInformationBuilder().withCca(TRACK_AND_FIELD)
            .withRole("Vice-Captain").withAttendance(12).build();
    public static final CcaInformation DANIEL_VOLLEYBALL = new CcaInformationBuilder().withCca(VOLLEYBALL)
            .withRole("Member").withAttendance(8).build();
    public static final CcaInformation ELLE_BADMINTON = new CcaInformationBuilder().withCca(BADMINTON)
            .withRole("Vice-Captain").withAttendance(14).build();
    public static final CcaInformation ELLE_GARDENING = new CcaInformationBuilder().withCca(GARDENING)
            .withRole("President").withAttendance(17).build();
    public static final CcaInformation FIONA_SWIMMING = new CcaInformationBuilder().withCca(SWIMMING)
            .withRole("Member").withAttendance(8).build();
    public static final CcaInformation FIONA_TENNIS = new CcaInformationBuilder().withCca(TENNIS)
            .withRole("Member").withAttendance(12).build();
    public static final CcaInformation GEORGE_BASKETBALL = new CcaInformationBuilder().withCca(BASKETBALL)
            .withRole("Member").withAttendance(11).build();
    public static final CcaInformation GEORGE_GARDENING = new CcaInformationBuilder().withCca(GARDENING)
            .withRole("Vice-President").withAttendance(12).build();
    public static final CcaInformation HOON_TABLE_TENNIS = new CcaInformationBuilder().withCca(TABLE_TENNIS)
            .withRole("Vice-Captain").withAttendance(13).build();
    public static final CcaInformation HOON_VOLLEYBALL = new CcaInformationBuilder().withCca(VOLLEYBALL)
            .withRole("Captain").withAttendance(8).build();
    public static final CcaInformation IDA_ACTING = new CcaInformationBuilder().withCca(ACTING)
            .withRole("Member").withAttendance(4).build();
    public static final CcaInformation IDA_TRACK_AND_FIELD = new CcaInformationBuilder().withCca(TRACK_AND_FIELD)
            .withRole("Member").withAttendance(11).build();

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com").withPhone("94351253")
            .withCcaInformations(Set.of(ALICE_ACTING, ALICE_BASKETBALL)).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
            .withCcaInformations(Set.of(BENSON_BADMINTON, BENSON_SWIMMING)).build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withAddress("231 Pioneer Road, #05-05").withEmail("carl@example.com").withPhone("95352563")
            .withCcaInformations(Set.of(CARL_TABLE_TENNIS, CARL_TENNIS)).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withAddress("10th street, #05-69").withEmail("daniel@example.com").withPhone("87652533")
            .withCcaInformations(Set.of(DANIEL_TRACK_AND_FIELD, DANIEL_VOLLEYBALL)).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withAddress("145 Michigan ave").withEmail("elle@example.com").withPhone("9482224")
            .withCcaInformations(Set.of(ELLE_BADMINTON, ELLE_GARDENING)).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withAddress("Little tokyo").withEmail("fiona@example.com").withPhone("9482427")
            .withCcaInformations(Set.of(FIONA_SWIMMING, FIONA_TENNIS)).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withAddress("4th street").withEmail("anna@example.com").withPhone("9482442")
            .withCcaInformations(Set.of(GEORGE_BASKETBALL, GEORGE_GARDENING)).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withCcaInformations(Set.of(HOON_TABLE_TENNIS, HOON_VOLLEYBALL)).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withCcaInformations(Set.of(IDA_ACTING, IDA_TRACK_AND_FIELD)).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Cca cca : TypicalCcas.getTypicalCcas()) {
            ab.addCca(cca);
        }

        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
