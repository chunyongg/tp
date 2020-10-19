package seedu.address.model.managers;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Optional;

import seedu.address.model.group.Group;
import seedu.address.model.group.Lesson;
import seedu.address.model.util.UniqueList;

public class LessonManager {
    private final HashMap<Group, UniqueList<Lesson>> lessonLists;
    public LessonManager() {
        lessonLists = new HashMap<>();
    }


    /**
     * Adds a given Lesson to a Group
     * @param group
     * @param lesson
     */
    public void addLesson(Group group, Lesson lesson) {
        requireAllNonNull(group, lesson);
        UniqueList<Lesson> lessonList = lessonLists.get(group);
        if (lessonList != null) {
            lessonList.add(lesson);
        }
    }

    /**
     * Replaces the lessons of {@code group} with {@code lessons}
     * @param group
     * @param lessons
     */
    public void setLessonLists(Group group, UniqueList<Lesson> lessons) {
        requireAllNonNull(group, lessons);
        lessonLists.put(group, lessons);
    }

    public Optional<UniqueList<Lesson>> getLessons(Group group) {
        return Optional.ofNullable(lessonLists.get(group));
    }

}
