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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.cca.Cca;
import seedu.address.model.role.Role;

/**
 * A utility class containing a list of {@code Cca} objects to be used in tests.
 */
public class TypicalCcas {

    public static final int SMALL_TOTAL_SESSIONS = 10;
    public static final int MEDIUM_TOTAL_SESSIONS = 15;
    public static final int LARGE_TOTAL_SESSIONS = 20;
    public static final Role PRESIDENT = new Role("President");
    public static final Role VICE_PRESIDENT = new Role("Vice-President");
    public static final Role MEMBER = new Role("Member");
    public static final Role CAPTAIN = new Role("Captain");
    public static final Role VICE_CAPTAIN = new Role("Vice-Captain");

    public static final Set<Role> ACTING_ROLES = new HashSet<>(Arrays.asList(PRESIDENT, VICE_PRESIDENT, MEMBER));
    public static final Cca ACTING = new CcaBuilder().withCcaName(VALID_CCA_NAME_ACTING)
            .withRoles(ACTING_ROLES).withTotalSessions(SMALL_TOTAL_SESSIONS).build();

    public static final Set<Role> BASKETBALL_ROLES = new HashSet<>(Arrays.asList(CAPTAIN, VICE_CAPTAIN, MEMBER));
    public static final Cca BASKETBALL = new CcaBuilder().withCcaName(VALID_CCA_NAME_BASKETBALL)
            .withRoles(BASKETBALL_ROLES).withTotalSessions(MEDIUM_TOTAL_SESSIONS).build();

    public static final Set<Role> BADMINTON_ROLES = new HashSet<>(Arrays.asList(CAPTAIN, VICE_CAPTAIN, MEMBER));
    public static final Cca BADMINTON = new CcaBuilder().withCcaName(VALID_CCA_NAME_BADMINTON)
            .withRoles(BADMINTON_ROLES).withTotalSessions(LARGE_TOTAL_SESSIONS).build();

    public static final Set<Role> GARDENING_ROLES = new HashSet<>(Arrays.asList(PRESIDENT, VICE_PRESIDENT, MEMBER));
    public static final Cca GARDENING = new CcaBuilder().withCcaName(VALID_CCA_NAME_GARDENING)
            .withRoles(GARDENING_ROLES).withTotalSessions(LARGE_TOTAL_SESSIONS).build();

    public static final Set<Role> SWIMMING_ROLES = new HashSet<>(Arrays.asList(CAPTAIN, VICE_CAPTAIN, MEMBER));
    public static final Cca SWIMMING = new CcaBuilder().withCcaName(VALID_CCA_NAME_SWIMMING)
            .withRoles(SWIMMING_ROLES).withTotalSessions(SMALL_TOTAL_SESSIONS).build();

    public static final Set<Role> TABLE_TENNIS_ROLES = new HashSet<>(Arrays.asList(CAPTAIN, VICE_CAPTAIN, MEMBER));
    public static final Cca TABLE_TENNIS = new CcaBuilder().withCcaName(VALID_CCA_NAME_TABLE_TENNIS)
            .withRoles(TABLE_TENNIS_ROLES).withTotalSessions(MEDIUM_TOTAL_SESSIONS).build();

    public static final Set<Role> TENNIS_ROLES = new HashSet<>(Arrays.asList(CAPTAIN, VICE_CAPTAIN, MEMBER));
    public static final Cca TENNIS = new CcaBuilder().withCcaName(VALID_CCA_NAME_TENNIS)
            .withRoles(TENNIS_ROLES).withTotalSessions(LARGE_TOTAL_SESSIONS).build();

    public static final Set<Role> TRACK_AND_FIELD_ROLES = new HashSet<>(Arrays.asList(CAPTAIN, VICE_CAPTAIN, MEMBER));
    public static final Cca TRACK_AND_FIELD = new CcaBuilder().withCcaName(VALID_CCA_NAME_TRACK_AND_FIELD)
            .withRoles(TRACK_AND_FIELD_ROLES).withTotalSessions(MEDIUM_TOTAL_SESSIONS).build();

    public static final Set<Role> VOLLEYBALL_ROLES = new HashSet<>(Arrays.asList(CAPTAIN, VICE_CAPTAIN, MEMBER));
    public static final Cca VOLLEYBALL = new CcaBuilder().withCcaName(VALID_CCA_NAME_VOLLEYBALL)
            .withRoles(VOLLEYBALL_ROLES).withTotalSessions(SMALL_TOTAL_SESSIONS).build();

    private TypicalCcas() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical ccas.
     */
    public static List<Cca> getTypicalCcas() {
        return new ArrayList<>(Arrays.asList(ACTING, BASKETBALL, BADMINTON, SWIMMING, TABLE_TENNIS, TENNIS,
                VOLLEYBALL, TRACK_AND_FIELD, GARDENING));
    }
}
