package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_GARDENING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCcas.BASKETBALL;
import static seedu.address.testutil.TypicalCcas.GARDENING;

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

        // name has trailing spaces -> returns false
        String nameWithTrailingSpaces = VALID_CCA_NAME_BASKETBALL + " ";
        editedBasketball = new CcaBuilder(BASKETBALL).withCcaName(nameWithTrailingSpaces).build();
        assertFalse(BASKETBALL.isSameCca(editedBasketball));
    }

    @Test
    public void equals() {
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
}
