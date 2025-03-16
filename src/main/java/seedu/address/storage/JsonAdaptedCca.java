package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cca.Cca;

/**
 * Jackson-friendly version of {@link Cca}.
 */
public class JsonAdaptedCca {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Cca's %s field is missing!";

    private final String ccaName;

    /**
     * Constructs a {@code JsonAdaptedCca} with the given {@code ccaName}.
     */
    @JsonCreator
    public JsonAdaptedCca(@JsonProperty("ccaName") String ccaName) {
        this.ccaName = ccaName;
    }

    /**
     * Converts a given {@code Cca} into this class for Jackson use.
     */
    public JsonAdaptedCca(Cca source) {
        ccaName = source.ccaName;
    }

    /**
     * Converts this Jackson-friendly adapted cca object into the model's {@code Cca} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted cca.
     */
    public Cca toModelType() throws IllegalValueException {
        if (!Cca.isValidCcaName(ccaName)) {
            throw new IllegalValueException(Cca.MESSAGE_CONSTRAINTS);
        }
        return new Cca(ccaName);
    }

}
