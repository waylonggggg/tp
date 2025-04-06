package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCcaToStudentCommand;
import seedu.address.model.cca.CcaName;
import seedu.address.testutil.TypicalCcas;


public class AddCcaToStudentCommandParserTest {

    // Use the CcaName constants from TypicalCcas and get their string value
    private static final String CCA_NAME_DESC_BASKETBALL = " " + PREFIX_CCA_NAME
            + TypicalCcas.CCA_NAME_BASKETBALL.fullCcaName; // Fixed
    private static final String CCA_NAME_DESC_TENNIS = " " + PREFIX_CCA_NAME
            + TypicalCcas.CCA_NAME_TENNIS.fullCcaName; // Fixed

    // Example of an invalid CCA name description based on CcaName constraints
    // Keep this definition or use one from CommandTestUtil if available
    private static final String INVALID_CCA_NAME_DESC = " " + PREFIX_CCA_NAME + "Basket*ball";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddCcaToStudentCommand.MESSAGE_USAGE);

    private static final String  MESSAGE_INVALID_FORMAT_PARSE_EXCEPTION =
            String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    "%s",
                    AddCcaToStudentCommand.MESSAGE_USAGE);

    private AddCcaToStudentCommandParser parser = new AddCcaToStudentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CCA_NAME_DESC_BASKETBALL, MESSAGE_INVALID_FORMAT);

        // no cca prefix specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no cca name specified (PREFIX_CCA_NAME followed by nothing)
        assertParseFailure(parser, "1 " + PREFIX_CCA_NAME, CcaName.MESSAGE_CONSTRAINTS);
        // Test also with space after prefix
        assertParseFailure(parser, "1 " + PREFIX_CCA_NAME + " ", CcaName.MESSAGE_CONSTRAINTS);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CCA_NAME_DESC_BASKETBALL,
                String.format(MESSAGE_INVALID_FORMAT_PARSE_EXCEPTION, MESSAGE_INVALID_INDEX));

        // zero index
        assertParseFailure(parser, "0" + CCA_NAME_DESC_BASKETBALL,
                String.format(MESSAGE_INVALID_FORMAT_PARSE_EXCEPTION, MESSAGE_INVALID_INDEX));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + CCA_NAME_DESC_BASKETBALL,
                String.format(MESSAGE_INVALID_FORMAT_PARSE_EXCEPTION, MESSAGE_INVALID_INDEX));

        // invalid prefix in preamble
        assertParseFailure(parser, "1 i/ string" + CCA_NAME_DESC_BASKETBALL,
                String.format(MESSAGE_INVALID_FORMAT_PARSE_EXCEPTION, MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid cca name (e.g., contains '*')
        assertParseFailure(parser, "1" + INVALID_CCA_NAME_DESC, CcaName.MESSAGE_CONSTRAINTS);

        // non-empty preamble before index - should fail index parsing
        assertParseFailure(parser, " abc 1" + CCA_NAME_DESC_BASKETBALL, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        // Use the corrected CCA_NAME_DESC_BASKETBALL definition
        String userInput = targetIndex.getOneBased() + CCA_NAME_DESC_BASKETBALL;
        CcaName expectedCcaName = TypicalCcas.CCA_NAME_BASKETBALL; // Use CcaName constant

        AddCcaToStudentCommand expectedCommand = new AddCcaToStudentCommand(targetIndex, expectedCcaName);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleFieldsSpecified_success() {
        // Test robustness with extra whitespace
        Index targetIndex = INDEX_FIRST_PERSON;
        // Use the corrected CCA_NAME_DESC_BASKETBALL definition
        String userInput = "  " + targetIndex.getOneBased() + "   " + CCA_NAME_DESC_BASKETBALL + "   ";
        CcaName expectedCcaName = TypicalCcas.CCA_NAME_BASKETBALL; // Use CcaName constant

        AddCcaToStudentCommand expectedCommand = new AddCcaToStudentCommand(targetIndex, expectedCcaName);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_duplicateFields_failure() {
        // Duplicate CCA prefixes
        // Use the corrected definitions for the description strings
        String userInput = INDEX_FIRST_PERSON.getOneBased() + CCA_NAME_DESC_BASKETBALL + CCA_NAME_DESC_TENNIS;
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CCA_NAME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing cca prefix
        assertParseFailure(parser, String.valueOf(INDEX_FIRST_PERSON.getOneBased()), MESSAGE_INVALID_FORMAT);

        // missing index
        assertParseFailure(parser, CCA_NAME_DESC_BASKETBALL, MESSAGE_INVALID_FORMAT);
    }

}
