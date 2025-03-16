package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCcas.BADMINTON;

import org.junit.jupiter.api.Test;

public class JsonAdaptedCcaTest {
    
    @Test
    public void toModelType_validCcaDetails_returnsCca() throws Exception {
        JsonAdaptedCca cca = new JsonAdaptedCca(BADMINTON);
        assertEquals(BADMINTON, cca.toModelType());
    }
}
