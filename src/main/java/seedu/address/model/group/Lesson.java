package seedu.address.model.group;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.util.UniqueList;

/**
 * Represents a tutorial class in serenity. Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Lesson {

    public static final String NAME_CONSTRAINT = "Class name cannot be empty";
    public static final String STUDENTS_INFO_CONSTRAINT = "Students information cannot be empty";

    private final String name;
    private final UniqueList<StudentInfo> studentsInfo;
    private final UniqueList<Question> questionList;

    /**
     * Constructs a {@code Lesson}.
     * @param name A valid name.
     * @param studentsInfo A valid list of studentInfo.
     */
    public Lesson(String name, UniqueList<StudentInfo> studentsInfo) {
        requireAllNonNull(name, studentsInfo);
        checkArgument(isValidName(name), NAME_CONSTRAINT);
        checkArgument(isValidStudentInfo(studentsInfo), STUDENTS_INFO_CONSTRAINT);
        this.name = name;
        this.studentsInfo = studentsInfo;
        this.questionList = new UniqueQuestionList();
    }

    /**
     * Constructs a {@code Lesson}.
     * @param name A valid name.
     * @param studentsInfo A valid list of studentInfo.
     * @param questionList A list of questions.
     */
    public Lesson(String name, UniqueList<StudentInfo> studentsInfo, UniqueList<Question> questionList) {
        requireAllNonNull(name, studentsInfo);
        checkArgument(isValidName(name), NAME_CONSTRAINT);
        checkArgument(isValidStudentInfo(studentsInfo), STUDENTS_INFO_CONSTRAINT);
        this.name = name;
        this.studentsInfo = studentsInfo;
        this.questionList = questionList;
    }



    private UniqueList<StudentInfo> generateStudentInfo(ObservableList<Student> students) {
        UniqueList<StudentInfo> studentInfo = new UniqueStudentInfoList();
        for (Student s : students) {
            studentInfo.add(new StudentInfo(s, new Participation(), new Attendance()));
        }
        return studentInfo;
    }

    boolean isValidName(String name) {
        return name.length() > 0;
    }

    public String getName() {
        return name;
    }

    boolean isValidStudentInfo(UniqueList<StudentInfo> studentsInfo) {
        return studentsInfo.size() > 0;
    }

    public UniqueList<StudentInfo> getStudentsInfo() {
        return studentsInfo;
    }

    public UniqueList<Question> getQuestionList() {
        return questionList;
    }

    public ObservableList<StudentInfo> getStudentsInfoAsUnmodifiableObservableList() {
        return studentsInfo.asUnmodifiableObservableList();
    }

    public boolean isSame(Lesson otherLsn) {
        return otherLsn.getName().equals(getName());
    }

    public ObservableList<Question> getQuestionListAsUnmodifiableObservableList() {
        return questionList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Lesson)) {
            return false;
        }

        Lesson otherClass = (Lesson) obj;
        return otherClass.getName().equals(getName())
                && otherClass.getStudentsInfo().equals(getStudentsInfo())
                && otherClass.getQuestionList().equals(getQuestionList());
    }

    @Override
    public String toString() {
        return String.format("Lesson %s", name);
    }
}

