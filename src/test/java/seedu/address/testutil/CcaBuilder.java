package seedu.address.testutil;

import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;

/**
 * A utility class to help with building CCA objects.
 */
public class CcaBuilder {

    public static final String DEFAULT_CCA_NAME = "basketball";

    private CcaName ccaName;

    /**
     * Creates a {@code CcaBuilder} with the default details.
     */
    public CcaBuilder() {
        ccaName = new CcaName(DEFAULT_CCA_NAME);
    }

    /**
     * Initializes the CcaBuilder with the data of {@code ccaToCopy}.
     */
    public CcaBuilder(Cca ccaToCopy) {
        ccaName = ccaToCopy.getCcaName();
    }

    /**
     * Sets the {@code Name} of the {@code Cca} that we are building.
     */
    public CcaBuilder withCcaName(String ccaName) {
        this.ccaName = new CcaName(ccaName);
        return this;
    }

    public Cca build() {
        return new Cca(ccaName);
    }

}
