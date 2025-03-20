package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CCA;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CCA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.cca.Cca;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCcaCommand}.
 */
public class DeleteCcaCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Cca ccaToDelete = model.getCcaList().get(INDEX_FIRST_CCA.getZeroBased());
        DeleteCcaCommand deleteCcaCommand = new DeleteCcaCommand(INDEX_FIRST_CCA);

        String expectedMessage = String.format(DeleteCcaCommand.MESSAGE_DELETE_CCA_SUCCESS,
                Messages.format(ccaToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCca(ccaToDelete);

        assertCommandSuccess(deleteCcaCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getCcaList().size() + 1);
        DeleteCcaCommand deleteCcaCommand = new DeleteCcaCommand(outOfBoundIndex);

        assertCommandFailure(deleteCcaCommand, model, Messages.MESSAGE_INVALID_CCA_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCcaCommand deleteFirstCommand = new DeleteCcaCommand(INDEX_FIRST_CCA);
        DeleteCcaCommand deleteSecondCommand = new DeleteCcaCommand(INDEX_SECOND_CCA);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCcaCommand deleteFirstCommandCopy = new DeleteCcaCommand(INDEX_FIRST_CCA);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCcaCommand deleteCcaCommand = new DeleteCcaCommand(targetIndex);
        String expected = DeleteCcaCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCcaCommand.toString());
    }
}
