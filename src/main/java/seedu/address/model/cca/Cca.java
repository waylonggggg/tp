package seedu.address.model.cca;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a CCA in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Cca {

    private final CcaName ccaName;

    /**
     * Constructs a {@code Cca}.
     *
     * @param ccaName A valid CCA name.
     */
    public Cca(CcaName ccaName) {
        requireNonNull(ccaName);
        this.ccaName = ccaName;
    }

    /**
     * Returns the name of the CCA.
     */
    public CcaName getCcaName() {
        return ccaName;
    }

    /**
     * Returns true if both ccas have the same cca name.
     * This defines a weaker notion of equality between two ccas.
     */
    public boolean isSameCca(Cca otherCca) {
        if (otherCca == this) {
            return true;
        }

        return otherCca != null
                && otherCca.getCcaName().equals(getCcaName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Cca)) {
            return false;
        }

        Cca otherCca = (Cca) other;
        return ccaName.equals(otherCca.ccaName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ccaName);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[" + ccaName + "]";
    }

}
