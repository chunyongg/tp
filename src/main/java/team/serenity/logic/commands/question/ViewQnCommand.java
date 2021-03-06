package team.serenity.logic.commands.question;

import static java.util.Objects.requireNonNull;
import static team.serenity.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static team.serenity.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static team.serenity.model.Model.PREDICATE_SHOW_ALL_QUESTIONS;

import team.serenity.logic.commands.Command;
import team.serenity.logic.commands.CommandResult;
import team.serenity.logic.commands.exceptions.CommandException;
import team.serenity.model.Model;

/**
 * Lists all questions to the user.
 */
public class ViewQnCommand extends Command {

    public static final String COMMAND_WORD = "viewqn";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Displays all questions from all tutorial groups and tutorial lessons as a list with index numbers.";
    public static final String MESSAGE_SUCCESS =
            "These are all the questions asked by students across all groups and lessons.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        model.updateFilteredQuestionList(PREDICATE_SHOW_ALL_QUESTIONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.UiAction.VIEW_QN);
    }

}
