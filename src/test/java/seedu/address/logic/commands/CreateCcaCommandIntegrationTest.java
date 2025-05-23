package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.cca.Cca;
import seedu.address.testutil.CcaBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code CreateStudentCommand}.
 */
public class CreateCcaCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newCca_success() {
        Cca validCca = new CcaBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addCca(validCca);

        assertCommandSuccess(new CreateCcaCommand(validCca), model,
                String.format(CreateCcaCommand.MESSAGE_SUCCESS, Messages.format(validCca)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCca_throwsCommandException() {
        Cca ccaInList = model.getAddressBook().getCcaList().get(0);
        assertCommandFailure(new CreateCcaCommand(ccaInList), model,
                Messages.MESSAGE_DUPLICATE_CCA);
    }

}
