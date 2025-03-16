package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.cca.Cca;

/**
 * A utility class containing a list of {@code Cca} objects to be used in tests.
 */
public class TypicalCcas {

    public static final Cca BASKETBALL = new CcaBuilder().withCcaName("basketball").build();
    public static final Cca GARDENING = new CcaBuilder().withCcaName("gardening").build();

    // Manually added
    public static final Cca ESPORTS = new CcaBuilder().withCcaName("e sports").build();
    public static final Cca DANCING = new CcaBuilder().withCcaName("dancing").build();

    private TypicalCcas() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical ccas.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Cca cca : getTypicalCcas()) {
            // ab.addCcas(cca); TODO: implement addCcas in the addressbook
        }
        return ab;
    }

    public static List<Cca> getTypicalCcas() {
        return new ArrayList<>(Arrays.asList(BASKETBALL, GARDENING, ESPORTS, DANCING));
    }
}
