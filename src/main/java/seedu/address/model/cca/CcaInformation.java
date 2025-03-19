package seedu.address.model.cca;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.role.Role;


public class CcaInformation {

    private final Cca cca;
    private final Role role;
    private final Attendance attendance;

    public CcaInformation(Cca cca, Role role, Attendance attendance) {
        requireAllNonNull(cca, role, attendance);
        this.cca = cca;
        this.role = role;
        this.attendance = attendance;
    }

    public Cca getCca() {
        return cca;
    }

    public Role getRole() {
        return role;
    }

    public Attendance getAttendance() {
        return attendance;
    }

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
