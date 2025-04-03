package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.cca.Cca;
import seedu.address.testutil.CcaBuilder;
import seedu.address.testutil.ModelStub;

public class CreateCcaCommandTest {

    @Test
    public void constructor_nullCca_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCcaCommand(null));
    }

    @Test
    public void execute_ccaAcceptedByModel_createSuccessful() throws Exception {
        ModelStubAcceptingCcaAdded modelStub = new ModelStubAcceptingCcaAdded();
        Cca validCca = new CcaBuilder().build();

        CommandResult commandResult = new CreateCcaCommand(validCca).execute(modelStub);

        assertEquals(String.format(CreateCcaCommand.MESSAGE_SUCCESS, Messages.format(validCca)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCca), modelStub.ccasAdded);
    }

    @Test
    public void execute_duplicateCca_throwsCommandException() {
        Cca validCca = new CcaBuilder().build();
        CreateCcaCommand createCcaCommand = new CreateCcaCommand(validCca);
        ModelStub modelStub = new ModelStubWithCca(validCca);

        assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_CCA, () -> createCcaCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Cca acting = new CcaBuilder().withCcaName("Acting").build();
        Cca basketball = new CcaBuilder().withCcaName("Basketball").build();
        CreateCcaCommand createActingCommand = new CreateCcaCommand(acting);
        CreateCcaCommand createBasketballCommand = new CreateCcaCommand(basketball);

        // same object -> returns true
        assertEquals(createActingCommand, createActingCommand);

        // same values -> returns true
        CreateCcaCommand createActingCommandCopy = new CreateCcaCommand(acting);
        assertEquals(createActingCommand, createActingCommandCopy);

        // different types -> returns false
        assertFalse(createActingCommand.equals(1));

        // null -> returns false
        assertFalse(createActingCommand.equals(null));

        // different cca -> returns false
        assertFalse(createActingCommand.equals(createBasketballCommand));
    }

    @Test
    public void toStringMethod() {
        Cca acting = new CcaBuilder().withCcaName("Acting").build();
        CreateCcaCommand createActingCommand = new CreateCcaCommand(acting);
        String expected = CreateCcaCommand.class.getCanonicalName() + "{toCreate=" + acting + "}";
        assertEquals(expected, createActingCommand.toString());
    }

    /**
     * A Model stub that contains a single cca.
     */
    private class ModelStubWithCca extends ModelStub {
        private final Cca cca;

        ModelStubWithCca(Cca cca) {
            requireNonNull(cca);
            this.cca = cca;
        }

        @Override
        public boolean hasCca(Cca cca) {
            requireNonNull(cca);
            return this.cca.isSameCca(cca);
        }
    }

    /**
     * A Model stub that always accept the cca being added.
     */
    private class ModelStubAcceptingCcaAdded extends ModelStub {
        final ArrayList<Cca> ccasAdded = new ArrayList<>();

        @Override
        public boolean hasCca(Cca cca) {
            requireNonNull(cca);
            return ccasAdded.stream().anyMatch(cca::isSameCca);
        }

        @Override
        public void addCca(Cca cca) {
            requireNonNull(cca);
            ccasAdded.add(cca);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
