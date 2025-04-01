package seedu.address.model.cca;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.role.Role;

/**
 * Represents a person's involvement in a Co-Curricular Activity (CCA).
 * Encapsulates the CCA, the person's role within the CCA, and their attendance record.
 */
public class CcaInformation {

    private final Cca cca;
    private final Role role;
    private final Attendance attendance;

    /**
     * Constructs a {@code CcaInformation} object.
     *
     * @param cca The CCA that the person is involved in.
     * @param role The role the person holds in the specified CCA.
     * @param attendance The attendance record of the person for the CCA.
     */
    public CcaInformation(Cca cca, Role role, Attendance attendance) {
        requireAllNonNull(cca, role, attendance);
        this.cca = cca;
        this.role = role;
        this.attendance = attendance;
    }

    /**
     * Returns the CCA associated with this {@code CcaInformation}.
     *
     * @return The CCA object.
     */
    public Cca getCca() {
        return cca;
    }

    /**
     * Returns the role of the person in the CCA.
     *
     * @return The {@code Role} object.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Returns the attendance record of the person in the CCA.
     *
     * @return The {@code Attendance} object.
     */
    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * Checks if the person can attend a specified number of sessions in the CCA.
     *
     * @param amount The number of sessions to check.
     * @return True if the person can attend the specified number of sessions, false otherwise.
     */
    public boolean canAttend(Amount amount) {
        return attendance.canAttend(amount);
    }

    /**
     * Returns the attendance record of the person in the CCA as an {@code Amount} object.
     *
     * @return The {@code Amount} object representing the attendance.
     */
    public CcaInformation attend(Amount amount) {
        return new CcaInformation(cca, role, attendance.attend(amount));
    }

    /**
     * Checks if this {@code CcaInformation} is equal to another object.
     * Two instances are considered equal if they have the same CCA, role, and attendance record.
     *
     * @param other The object to compare against.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CcaInformation)) {
            return false;
        }
        CcaInformation otherCcaInformation = (CcaInformation) other;
        return cca.equals(otherCcaInformation.cca)
            && role.equals(otherCcaInformation.role)
            && attendance.equals(otherCcaInformation.attendance);
    }

    @Override
    public int hashCode() {
        return cca.hashCode() + role.hashCode() + attendance.hashCode();
    }

    @Override
    public String toString() {
        return cca + " | Role: " + role + " | " + attendance;
    }
}
