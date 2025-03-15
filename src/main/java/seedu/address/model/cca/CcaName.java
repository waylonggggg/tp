package seedu.address.model.cca;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCcaName(String)}
 */
public class CcaName {

    public static final String MESSAGE_CONSTRAINTS =
            "Cca names must start with a letter, must not be empty or purely numeric, "
                    + "and may contain any characters thereafter.";

    /*
     * The string must start with a letter ([A-Za-z]).
     * After the first character, all characters are allowed ([\\s\\S]*).
     * This ensures that the string is never empty, be all digits nor start with whitespace or special characters.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z][\\s\\S]*$\n";

    public final String fullCcaName;

    /**
     * Constructs a {@code CcaName}.
     *
     * @param ccaName A valid cca name.
     */
    public CcaName(String ccaName) {
        requireNonNull(ccaName);
        checkArgument(isValidCcaName(ccaName), MESSAGE_CONSTRAINTS);
        fullCcaName = ccaName;
    }

    /**
     * Returns true if a given string is a valid cca name.
     */
    public static boolean isValidCcaName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullCcaName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CcaName)) {
            return false;
        }

        CcaName otherName = (CcaName) other;
        return fullCcaName.equals(otherName.fullCcaName);
    }

    @Override
    public int hashCode() {
        return fullCcaName.hashCode();
    }

}
