package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_BASKETBALL;
import static seedu.address.testutil.TypicalCcas.BASKETBALL;

import org.junit.jupiter.api.Test;

import seedu.address.model.cca.exceptions.CcaNotFoundException;
import seedu.address.model.cca.exceptions.DuplicateCcaException;
import seedu.address.testutil.CcaBuilder;

public class UniqueCcaListTest {
    private final UniqueCcaList uniqueCcaList = new UniqueCcaList();

    @Test
    public void contains_nullCca_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.contains(null));
    }

    @Test
    public void contains_ccaNotInList_returnsFalse() {
        assertFalse(uniqueCcaList.contains(BASKETBALL));
    }

    @Test
    public void contains_ccaInList_returnsTrue() {
        uniqueCcaList.add(BASKETBALL);
        assertTrue(uniqueCcaList.contains(BASKETBALL));
    }

    @Test
    public void contains_ccaWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCcaList.add(BASKETBALL);
        Cca editedBasketball = new CcaBuilder().withCcaName(VALID_CCA_NAME_BASKETBALL).build();
        assertTrue(uniqueCcaList.contains(editedBasketball));
    }

    @Test
    public void add_nullCca_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.add(null));
    }

    @Test
    public void add_duplicateCca_throwsDuplicateCcaException() {
        uniqueCcaList.add(BASKETBALL);
        assertThrows(DuplicateCcaException.class, () -> uniqueCcaList.add(BASKETBALL));
    }

    @Test
    public void setCca_nullUniqueCcaList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.setCcas((UniqueCcaList) null));
    }

    @Test
    public void remove_nullCca_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.remove(null));
    }

    @Test
    public void remove_ccaDoesNotExist_throwsCcaNotFoundException() {
        assertThrows(CcaNotFoundException.class, () -> uniqueCcaList.remove(BASKETBALL));
    }

    @Test
    public void setCcas_nullUniqueCcaList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.setCcas((UniqueCcaList) null));
    }
}
