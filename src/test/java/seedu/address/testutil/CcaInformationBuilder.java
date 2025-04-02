package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.cca.Attendance;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaInformation;
import seedu.address.model.cca.CcaName;
import seedu.address.model.cca.SessionCount;
import seedu.address.model.role.Role;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building CCAInformation objects.
 */
public class CcaInformationBuilder {

    public static final String DEFAULT_CCA_NAME = "Basketball";
    public static final int DEFAULT_SESSIONS_ATTENDED = 0;
    public static final int DEFAULT_TOTAL_SESSIONS = 0;

    private Cca cca;
    private Role role;
    private Attendance attendance;

    /**
     * Creates a {@code CcaInformationBuilder} with the default details.
     */
    public CcaInformationBuilder() {
        cca = new Cca(new CcaName(DEFAULT_CCA_NAME));
        role = Role.DEFAULT_ROLE;
        attendance = new Attendance(new SessionCount(DEFAULT_SESSIONS_ATTENDED),
                new SessionCount(DEFAULT_TOTAL_SESSIONS));
    }

    /**
     * Initializes the CcaInformationBuilder with the data of {@code ccaInformationToCopy}.
     *
     * @param ccaInformationToCopy The CcaInformation instance to copy from.
     */
    public CcaInformationBuilder(CcaInformation ccaInformationToCopy) {
        cca = ccaInformationToCopy.getCca();
        role = ccaInformationToCopy.getRole();
        attendance = ccaInformationToCopy.getAttendance();
    }

    /**
     * Sets the {@code Cca} of the {@code CcaInformation} that we are building.
     */
    public CcaInformationBuilder withCca(Cca cca) {
        this.cca = cca;
        return this;
    }

    /**
     * Sets the {@code role} of the {@code CcaInformation} that we are building.
     */
    public CcaInformationBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code CcaInformation} that we are building.
     */
    public CcaInformationBuilder withAttendance(int sessionsAttended) {
        this.attendance = new Attendance(new SessionCount(sessionsAttended), cca.getTotalSessions());
        return this;
    }

    /**
     * Builds and returns the {@code CcaInformation} object.
     *
     * @return A new instance of {@code CcaInformation}.
     */
    public CcaInformation build() {
        return new CcaInformation(cca, role, attendance);
    }

}
