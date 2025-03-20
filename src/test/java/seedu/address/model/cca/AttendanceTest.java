package seedu.address.model.cca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void constructor_validAttendance_success() {
        SessionCount sessionsAttended = new SessionCount(3);
        SessionCount totalSessions = new SessionCount(5);
        Attendance attendance = new Attendance(sessionsAttended, totalSessions);
        assertEquals(sessionsAttended, attendance.getSessionsAttended());
        assertEquals(totalSessions, attendance.getTotalSessions());
    }

    @Test
    public void constructor_attendanceExceedsTotal_throwsIllegalArgumentException() {
        SessionCount sessionsAttended = new SessionCount(6);
        SessionCount totalSessions = new SessionCount(5);
        assertThrows(IllegalArgumentException.class, () -> new Attendance(sessionsAttended, totalSessions));
    }

    @Test
    public void constructor_nullValues_throwsNullPointerException() {
        SessionCount validSessions = new SessionCount(3);
        assertThrows(NullPointerException.class, () -> new Attendance(null, validSessions));
        assertThrows(NullPointerException.class, () -> new Attendance(validSessions, null));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        SessionCount sessionsAttended = new SessionCount(2);
        SessionCount totalSessions = new SessionCount(5);
        Attendance attendance1 = new Attendance(sessionsAttended, totalSessions);
        Attendance attendance2 = new Attendance(new SessionCount(2), new SessionCount(5));
        assertEquals(attendance1, attendance2);
    }

    @Test
    public void hashCode_sameValues_returnsSameHashCode() {
        SessionCount sessionsAttended = new SessionCount(2);
        SessionCount totalSessions = new SessionCount(5);
        Attendance attendance1 = new Attendance(sessionsAttended, totalSessions);
        Attendance attendance2 = new Attendance(new SessionCount(2), new SessionCount(5));
        assertEquals(attendance1.hashCode(), attendance2.hashCode());
    }

    @Test
    public void toString_validAttendance_returnsCorrectString() {
        SessionCount sessionsAttended = new SessionCount(3);
        SessionCount totalSessions = new SessionCount(5);
        Attendance attendance = new Attendance(sessionsAttended, totalSessions);
        String expected = "Attendance: 3/5";
        assertEquals(expected, attendance.toString());
    }
}
