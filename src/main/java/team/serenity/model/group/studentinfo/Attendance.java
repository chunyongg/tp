package team.serenity.model.group.studentinfo;

/**
 * Represents a {@code Student} attendance in a Lesson.
 */
public class Attendance {

    private final boolean isPresent;
    private final boolean isFlagged;

    /**
     * Creates a default Attendance object that is not flagged and not present.
     */
    public Attendance() {
        this.isFlagged = false;
        this.isPresent = false;
    }

    /**
     * Creates an Attendance object that is not flagged and with presence marked accordingly.
     * @param isPresent True if student is present and false if student is absent
     */
    public Attendance(boolean isPresent) {
        this.isPresent = isPresent;
        this.isFlagged = false;
    }

    /**
     * Creates an Attendance object that isPresent and with flag marked accordingly.
     * @param isPresent True if student is present and false if student is absent
     * @param isFlagged True to flag student and false to unflag student
     */
    public Attendance(boolean isPresent, boolean isFlagged) {
        this.isPresent = isPresent;
        this.isFlagged = isFlagged;
    }

    @Override
    public String toString() {
        return Boolean.toString(this.isPresent);
    }

    public boolean getAttendance() {
        return this.isPresent;
    }

    public Attendance setNewAttendance(boolean isPresent) {
        Attendance updatedAttendance = new Attendance(isPresent);
        return updatedAttendance;
    }

    public boolean getFlagged() {
        return this.isFlagged;
    }

    @Override
    public boolean equals(Object obj) {
        Attendance other = (Attendance) obj;
        return other.getAttendance() == getAttendance() && other.getFlagged() == getFlagged();
    }
}
