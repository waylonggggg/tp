package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_GARDENING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCcas.BASKETBALL;
import static seedu.address.testutil.TypicalCcas.CAPTAIN;
import static seedu.address.testutil.TypicalCcas.GARDENING;
import static seedu.address.testutil.TypicalCcas.MEDIUM_TOTAL_SESSIONS;
import static seedu.address.testutil.TypicalCcas.MEMBER;
import static seedu.address.testutil.TypicalCcas.VICE_CAPTAIN;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CcaBuilder;

public class CcaTest {

    @Test
    public void isSameCca() {
        // same object -> returns true
        assertTrue(BASKETBALL.isSameCca(BASKETBALL));

        // null -> returns false
        assertFalse(BASKETBALL.isSameCca(null));

        // different name -> returns false
        Cca editedBasketball = new CcaBuilder(BASKETBALL).withCcaName(VALID_CCA_NAME_GARDENING).build();
        assertFalse(BASKETBALL.isSameCca(editedBasketball));

        // name has extra word -> returns false
        String nameWithExtraWord = VALID_CCA_NAME_BASKETBALL + " Male";
        editedBasketball = new CcaBuilder(BASKETBALL).withCcaName(nameWithExtraWord).build();
        assertFalse(BASKETBALL.isSameCca(editedBasketball));
    }

    @Test
    public void equals() {
        Cca basketball = new CcaBuilder().withCcaName("Basketball").withRoles("Captain", "Player")
                .withTotalSessions(15).build();
        Cca sameBasketball = new CcaBuilder().withCcaName("Basketball").withRoles("Captain", "Player")
                .withTotalSessions(15).build();
        Cca differentBasketball = new CcaBuilder().withCcaName("Basketball").withRoles("Coach")
                .withTotalSessions(10).build();

        // same values -> should be equal
        assertEquals(basketball, sameBasketball);

        // different roles or sessions -> should not be equal
        assertFalse(basketball.equals(differentBasketball));

        // same values -> returns true
        Cca basketballCopy = new CcaBuilder(BASKETBALL).build();
        assertTrue(BASKETBALL.equals(basketballCopy));

        // same object -> returns true
        assertTrue(BASKETBALL.equals(BASKETBALL));

        // null -> returns false
        assertFalse(BASKETBALL.equals(null));

        // different type -> returns false
        assertFalse(BASKETBALL.equals(5));

        // different cca -> returns false
        assertFalse(BASKETBALL.equals(GARDENING));

        // different name -> returns false
        Cca editedBasketball = new CcaBuilder(BASKETBALL).withCcaName(VALID_CCA_NAME_GARDENING).build();
        assertFalse(BASKETBALL.equals(editedBasketball));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cca(null));
    }

    @Test
    public void toStringMethod() {
        String expected = "[" + BASKETBALL.getCcaName() + "]";
        assertEquals(expected, BASKETBALL.toString());
    }

    @Test
    public void getRoles() {
        assertEquals(Set.of(MEMBER, CAPTAIN, VICE_CAPTAIN), BASKETBALL.getRoles());
    }

    @Test
    public void getTotalSessions() {
        assertEquals(MEDIUM_TOTAL_SESSIONS, BASKETBALL.getTotalSessions().getSessionCount());
    }
}
