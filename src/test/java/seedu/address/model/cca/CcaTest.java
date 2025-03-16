package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CcaTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cca(null));
    }

    @Test
    public void constructor_invalidCcaName_throwsIllegalArgumentException() {
        String invalidCcaName = "";
        assertThrows(IllegalArgumentException.class, () -> new Cca(invalidCcaName));
    }

    @Test
    public void isValidCcaName() {
        // null cca name
        assertThrows(NullPointerException.class, () -> Cca.isValidCcaName(null));
    }

    @Test
    public void isSameCca() {
        // null cca name
        assertFalse(new Cca("Basketball").isSameCca(null));
        
        // same cca name
        Cca basketball = new Cca("Basketball");
        Cca basketballCopy = new Cca("Basketball");
        assertTrue(basketball.isSameCca(basketballCopy));

        // different cca name
        Cca badminton = new Cca("Badminton");
        assertFalse(basketball.isSameCca(badminton));

        // same object
        assertTrue(basketball.isSameCca(basketball));
    }

}
