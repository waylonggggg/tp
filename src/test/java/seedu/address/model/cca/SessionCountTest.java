package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CcaBuilder;

public class SessionCountTest {

    @Test
    public void incrementSessionCount() {
        Cca basketball = new CcaBuilder().withCcaName("Basketball").withRoles("Captain", "Player")
                .withTotalSessions(15).build();
        Cca updatedBasketball = new Cca(basketball.getCcaName(), basketball.getRoles(),
                new SessionCount(basketball.getTotalSessions().getSessionCount() + 1));
        assertEquals(16, updatedBasketball.getTotalSessions().getSessionCount());
    }

    @Test
    public void decrementSessionCount() {
        Cca basketball = new CcaBuilder().withCcaName("Basketball").withRoles("Captain", "Player")
                .withTotalSessions(15).build();
        Cca updatedBasketball = new Cca(basketball.getCcaName(), basketball.getRoles(),
                new SessionCount(basketball.getTotalSessions().getSessionCount() - 1));
        assertEquals(14, updatedBasketball.getTotalSessions().getSessionCount());
    }
}
