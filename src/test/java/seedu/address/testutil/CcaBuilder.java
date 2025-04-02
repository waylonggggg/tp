package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;
import seedu.address.model.cca.SessionCount;
import seedu.address.model.role.Role;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building CCA objects.
 */
public class CcaBuilder {

    public static final String DEFAULT_CCA_NAME = "Handball";
    public static final int DEFAULT_TOTAL_SESSIONS = 0;

    private CcaName ccaName;
    private Set<Role> roles;
    private SessionCount totalSessions;

    /**
     * Creates a {@code CcaBuilder} with the default details.
     */
    public CcaBuilder() {
        ccaName = new CcaName(DEFAULT_CCA_NAME);
        roles = new HashSet<>();
        roles.add(Role.DEFAULT_ROLE);
        totalSessions = new SessionCount(DEFAULT_TOTAL_SESSIONS);
    }

    /**
     * Initializes the CcaBuilder with the data of {@code ccaToCopy}.
     *
     * @param ccaToCopy The Cca instance to copy from.
     */
    public CcaBuilder(Cca ccaToCopy) {
        ccaName = ccaToCopy.getCcaName();
        roles = new HashSet<>(ccaToCopy.getRoles());
        roles.add(Role.DEFAULT_ROLE);
        totalSessions = ccaToCopy.getTotalSessions();
    }

    /**
     * Sets the {@code Name} of the {@code Cca} that we are building.
     */
    public CcaBuilder withCcaName(String ccaName) {
        this.ccaName = new CcaName(ccaName);
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code Cca} that we are building.
     */
    public CcaBuilder withRoles(String... roles) {
        this.roles = SampleDataUtil.getRoleSet(roles);
        return this;
    }

    /**
     * Sets the {@code roles} of the {@code Cca} that we are building.
     */
    public CcaBuilder withRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    /**
     * Sets the {@code totalSessions} of the {@code Cca} that we are building.
     *
     * @param totalSessions The total number of sessions.
     * @return The current {@code CcaBuilder} instance.
     */
    public CcaBuilder withTotalSessions(int totalSessions) {
        this.totalSessions = new SessionCount(totalSessions);
        return this;
    }

    /**
     * Builds and returns the {@code Cca} object.
     *
     * @return A new instance of {@code Cca}.
     */
    public Cca build() {
        return new Cca(ccaName, roles, totalSessions);
    }

}
