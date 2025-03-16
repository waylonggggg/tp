package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_ACTING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_BADMINTON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_GARDENING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_TABLE_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_TRACK_AND_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_VOLLEYBALL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.cca.Cca;

/**
 * A utility class containing a list of {@code Cca} objects to be used in tests.
 */
public class TypicalCcas {

    public static final Cca ACTING = new CcaBuilder().withCcaName(VALID_CCA_NAME_ACTING).build();
    public static final Cca BASKETBALL = new CcaBuilder().withCcaName(VALID_CCA_NAME_BASKETBALL).build();
    public static final Cca BADMINTON = new CcaBuilder().withCcaName(VALID_CCA_NAME_BADMINTON).build();
    public static final Cca SWIMMING = new CcaBuilder().withCcaName(VALID_CCA_NAME_SWIMMING).build();
    public static final Cca TABLE_TENNIS = new CcaBuilder().withCcaName(VALID_CCA_NAME_TABLE_TENNIS).build();
    public static final Cca TENNIS = new CcaBuilder().withCcaName(VALID_CCA_NAME_TENNIS).build();
    public static final Cca VOLLEYBALL = new CcaBuilder().withCcaName(VALID_CCA_NAME_VOLLEYBALL).build();
    public static final Cca TRACK_AND_FIELD = new CcaBuilder().withCcaName(VALID_CCA_NAME_TRACK_AND_FIELD).build();
    public static final Cca GARDENING = new CcaBuilder().withCcaName(VALID_CCA_NAME_GARDENING).build();

    private TypicalCcas() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical ccas.
     */
    public static List<Cca> getTypicalCcas() {
        return new ArrayList<>(Arrays.asList(ACTING, BASKETBALL, BADMINTON, SWIMMING, TABLE_TENNIS, TENNIS,
                VOLLEYBALL, TRACK_AND_FIELD));
    }
}
