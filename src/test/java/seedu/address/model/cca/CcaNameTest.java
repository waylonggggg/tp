package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CcaNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CcaName(null));
    }

    @Test
    public void constructor_invalidCcaName_throwsIllegalArgumentException() {
        String invalidCcaName = "";
        assertThrows(IllegalArgumentException.class, () -> new CcaName(invalidCcaName));
    }

    @Test
    public void isValidCcaName() {
        // null cca name
        assertThrows(NullPointerException.class, () -> CcaName.isValidCcaName(null));

        // invalid cca name
        assertFalse(CcaName.isValidCcaName("")); // empty string
        assertFalse(CcaName.isValidCcaName(" ")); // spaces only
        assertFalse(CcaName.isValidCcaName("^")); // starting with non-alphabet characters

        // valid cca name
        assertTrue(CcaName.isValidCcaName("NUS hackers")); // alphabets only
        assertTrue(CcaName.isValidCcaName("Dancing 1st")); // alphabets followed by alphanumeric characters
        assertTrue(CcaName.isValidCcaName("cartoon#^#")); // alphabets followed by special characters
        assertTrue(CcaName.isValidCcaName("NUS GDG")); // with capital letters
        assertTrue(CcaName.isValidCcaName("Nus google data analytics associate groups")); // long cca names
    }

    @Test
    public void equals() {
        CcaName ccaName = new CcaName("Valid CcaName");

        // same values -> returns true
        assertTrue(ccaName.equals(new CcaName("Valid CcaName")));

        // same object -> returns true
        assertTrue(ccaName.equals(ccaName));

        // null -> returns false
        assertFalse(ccaName.equals(null));

        // different types -> returns false
        assertFalse(ccaName.equals(5.0f));

        // different values -> returns false
        assertFalse(ccaName.equals(new CcaName("Other Valid CcaName")));
    }
}
