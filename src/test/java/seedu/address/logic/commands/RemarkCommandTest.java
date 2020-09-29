package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

class RemarkCommandTest {

    private static final String REMARK = "Remark";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        //create default person
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        //create person after remark added
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(REMARK).build();
        //create remark command with default person and remark of editedPerson
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(editedPerson.getRemark().value));
        //get expected success message
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);
        //get expected model without new person yet
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        //change default person to person we are editing
        expectedModel.setPerson(firstPerson, editedPerson);
        //run assert to check that executing remarkCommand with model will yield expectedMessage and that model will be the same as expectedModel after execution
        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredPersonList().size());
        RemarkCommand command = new RemarkCommand(outOfBoundIndex, new Remark(REMARK));
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
