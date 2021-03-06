package team.serenity.logic.commands.question;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.serenity.logic.commands.CommandTestUtil.EDITED_QN_A;
import static team.serenity.logic.commands.CommandTestUtil.EDITED_QN_B;
import static team.serenity.logic.commands.CommandTestUtil.VALID_GROUP_NAME_G05;
import static team.serenity.logic.commands.CommandTestUtil.VALID_LESSON_NAME_1_2;
import static team.serenity.logic.commands.CommandTestUtil.VALID_QN_DESC_B;
import static team.serenity.testutil.question.TypicalQuestion.QUESTION_EDIT;

import org.junit.jupiter.api.Test;

import team.serenity.logic.commands.question.EditQnCommand.EditQuestionDescriptor;
import team.serenity.testutil.question.EditQuestionDescriptorBuilder;

public class EditQuestionDescriptorTest {

    @Test
    public void isAnyFieldEdited_allFieldsEdited_returnsTrue() {
        EditQuestionDescriptor descriptorWithAllFieldsEdited = new EditQuestionDescriptor(EDITED_QN_A);
        assertTrue(descriptorWithAllFieldsEdited.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_someFieldsEdited_returnsTrue() {
        EditQuestionDescriptor descriptorWithSomeFieldsEdited = new EditQuestionDescriptor();
        descriptorWithSomeFieldsEdited.setDescription(QUESTION_EDIT.getDescription());
        assertTrue(descriptorWithSomeFieldsEdited.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_noFieldsEdited_returnsFalse() {
        assertFalse(new EditQuestionDescriptor().isAnyFieldEdited());
    }

    @Test
    public void equals() {
        // same values -> returns true
        EditQuestionDescriptor descriptorWithSameValues = new EditQuestionDescriptor(EDITED_QN_A);
        assertTrue(EDITED_QN_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDITED_QN_A.equals(EDITED_QN_A));

        // null -> returns false
        assertFalse(EDITED_QN_A.equals(null));

        // different types -> returns false
        assertFalse(EDITED_QN_A.equals(5));

        // different values -> returns false
        assertFalse(EDITED_QN_A.equals(EDITED_QN_B));

        // different group name -> returns false
        EditQuestionDescriptor editedQuestion =
                new EditQuestionDescriptorBuilder(EDITED_QN_A).withGroupName(VALID_GROUP_NAME_G05).build();
        assertFalse(EDITED_QN_A.equals(editedQuestion));

        // different lesson name -> returns false
        editedQuestion =
                new EditQuestionDescriptorBuilder(EDITED_QN_A).withLessonName(VALID_LESSON_NAME_1_2).build();
        assertFalse(EDITED_QN_A.equals(editedQuestion));

        // different description -> returns false
        editedQuestion = new EditQuestionDescriptorBuilder(EDITED_QN_A).withDescription(VALID_QN_DESC_B).build();
        assertFalse(EDITED_QN_A.equals(editedQuestion));

    }

}
