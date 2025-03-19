package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CCA_NAME_DESC_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CCA_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_BASKETBALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCcas.BASKETBALL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateCcaCommand;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;
import seedu.address.testutil.CcaBuilder;

public class CreateCcaCommandParserTest {

    private CreateCcaCommandParser parser = new CreateCcaCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Cca expectedCca = new CcaBuilder(BASKETBALL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CCA_NAME_DESC_BASKETBALL,
                new CreateCcaCommand(expectedCca));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateCcaCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_CCA_NAME_BASKETBALL, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_CCA_NAME_DESC, CcaName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CCA_NAME_DESC_BASKETBALL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCcaCommand.MESSAGE_USAGE));
    }
}
