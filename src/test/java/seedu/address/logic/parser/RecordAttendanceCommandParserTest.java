package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_NAME_BASKETBALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.RecordAttendanceCommand;
import seedu.address.model.cca.Amount;
import seedu.address.model.cca.CcaName;


public class RecordAttendanceCommandParserTest {

    private RecordAttendanceCommandParser parser = new RecordAttendanceCommandParser();

    @Test
    public void parse_validArgs_returnsRecordAttendanceCommand() {
        // no leading and trailing whitespaces
        RecordAttendanceCommand expectedRecordAttendanceCommand =
                new RecordAttendanceCommand(INDEX_FIRST_PERSON, new CcaName(VALID_CCA_NAME_BASKETBALL), new Amount(1));
        assertParseSuccess(parser, "1 n/Basketball a/1", expectedRecordAttendanceCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "1 n/ Basketball a/ 1", expectedRecordAttendanceCommand);

        // higher amount
        RecordAttendanceCommand expectedRecordAttendanceCommandHigherAmount =
                new RecordAttendanceCommand(INDEX_FIRST_PERSON, new CcaName(VALID_CCA_NAME_BASKETBALL), new Amount(4));
        assertParseSuccess(parser, "1 n/Basketball a/4", expectedRecordAttendanceCommandHigherAmount);

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // non integer amount
        assertParseFailure(parser, "1 n/Basketball a/a", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                RecordAttendanceCommand.MESSAGE_USAGE));

        // missing amount prefix
        assertParseFailure(parser, "1 n/Basketball 1", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                RecordAttendanceCommand.MESSAGE_USAGE));

        // missing cca name prefix
        assertParseFailure(parser, "1 Basketball a/1", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                RecordAttendanceCommand.MESSAGE_USAGE));
    }

}
