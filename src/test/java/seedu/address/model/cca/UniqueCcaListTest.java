package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.cca.exceptions.CcaNotFoundException;
import seedu.address.model.cca.exceptions.DuplicateCcaException;

public class UniqueCcaListTest {
    private final UniqueCcaList uniqueCcaList = new UniqueCcaList();

    @Test
    public void contains_nullCca_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.contains(null));
    }

    @Test
    public void contains_ccaNotInList_returnsFalse() {
        assertFalse(uniqueCcaList.contains(new Cca("Basketball")));
    }

    @Test
    public void contains_ccaInList_returnsTrue() {
        uniqueCcaList.add(new Cca("Basketball"));
        assertTrue(uniqueCcaList.contains(new Cca("Basketball")));
    }

    @Test
    public void contains_ccaWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCcaList.add(new Cca("Basketball"));
        assertTrue(uniqueCcaList.contains(new Cca("Basketball")));
    }

    @Test
    public void add_nullCca_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.add(null));
    }

    @Test
    public void add_duplicateCca_throwsDuplicateCcaException() {
        uniqueCcaList.add(new Cca("Basketball"));
        assertThrows(DuplicateCcaException.class, () -> uniqueCcaList.add(new Cca("Basketball")));
    }

    @Test
    public void remove_nullCca_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.remove(null));
    }

    @Test
    public void remove_ccaDoesNotExist_throwsCcaNotFoundException() {
        assertThrows(CcaNotFoundException.class, () -> uniqueCcaList.remove(new Cca("Basketball")));
    }

    @Test
    public void setCcas_nullUniqueCcaList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCcaList.setCcas((UniqueCcaList) null));
    }
}
