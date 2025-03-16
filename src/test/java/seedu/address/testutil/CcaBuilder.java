package seedu.address.testutil;

import seedu.address.model.cca.Cca;

public class CcaBuilder {
    
    public static final String DEFAULT_CCA_NAME = "Basketball";
    
    private String ccaName;

    public CcaBuilder() {
        ccaName = DEFAULT_CCA_NAME;
    }

    /**
     * Initializes the CcaBuilder with the data of {@code ccaToCopy}.
     */
    public CcaBuilder(Cca ccaToCopy) {
        ccaName = ccaToCopy.getCcaName();
    }

    /**
     * Sets the ccaName of the {@code Cca} that we are building.
     */
    public CcaBuilder withCcaName(String ccaName) {
        this.ccaName = ccaName;
        return this;
    }

    public Cca build() {
        return new Cca(ccaName);
    }



}
