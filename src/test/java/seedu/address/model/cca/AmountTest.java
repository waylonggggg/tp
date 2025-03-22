package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class AmountTest {

    @Test
    public void constructor_validAmount_success() {
        Amount amount = new Amount(3);
        assertEquals(3, amount.getAmount());
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        // negative amount
        assertThrows(IllegalArgumentException.class, () -> new Amount(-1));
        // zero amount
        assertThrows(IllegalArgumentException.class, () -> new Amount(0));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Amount amount1 = new Amount(2);
        Amount amount2 = new Amount(2);
        assertEquals(amount1, amount2);
    }

    @Test
    public void isValidAmount() {
        // valid amount
        assertEquals(true, Amount.isValidAmount(1));
        // invalid amount
        assertEquals(false, Amount.isValidAmount(0));
        assertEquals(false, Amount.isValidAmount(-1));
    }

}
