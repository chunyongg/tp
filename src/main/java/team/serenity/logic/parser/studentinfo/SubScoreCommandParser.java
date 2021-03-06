package team.serenity.logic.parser.studentinfo;

import static team.serenity.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.serenity.commons.core.Messages.MESSAGE_SCORE_TO_SUB;
import static team.serenity.logic.parser.CliSyntax.PREFIX_ADD_SCORE;
import static team.serenity.logic.parser.CliSyntax.PREFIX_MATRIC;
import static team.serenity.logic.parser.CliSyntax.PREFIX_NAME;
import static team.serenity.logic.parser.CliSyntax.PREFIX_SUBTRACT_SCORE;

import java.util.Optional;
import java.util.stream.Stream;

import team.serenity.commons.core.index.Index;
import team.serenity.logic.commands.studentinfo.SubScoreCommand;
import team.serenity.logic.parser.ArgumentMultimap;
import team.serenity.logic.parser.ArgumentTokenizer;
import team.serenity.logic.parser.Parser;
import team.serenity.logic.parser.Prefix;
import team.serenity.logic.parser.SerenityParserUtil;
import team.serenity.logic.parser.exceptions.ParseException;
import team.serenity.model.group.student.Student;
import team.serenity.model.group.student.StudentName;
import team.serenity.model.group.student.StudentNumber;
import team.serenity.model.group.studentinfo.Participation;

/**
 * Parses input arguments and creates a new SubScoreCommand object.
 * Current support:
 * subscore name/NAME id/STUDENT_NUMBER sub/SCORE_TO_SUBTRACT
 * subscore INDEX sub/SCORE_TO_SUBTRACT
 */
public class SubScoreCommandParser implements Parser<SubScoreCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SubScoreCommand and
     * returns a SubScoreCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SubScoreCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput,
                PREFIX_NAME, PREFIX_MATRIC, PREFIX_SUBTRACT_SCORE);

        Index index;
        StudentName studentName;
        StudentNumber studentNumber;
        Optional<Student> student;
        int scoreToSub;

        if (!arePrefixesPresent(argMultimap, PREFIX_SUBTRACT_SCORE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubScoreCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && !argMultimap.getValue(PREFIX_MATRIC).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubScoreCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getValue(PREFIX_MATRIC).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubScoreCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getValue(PREFIX_MATRIC).isPresent()
                && argMultimap.getPreamble().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubScoreCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getPreamble().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubScoreCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_MATRIC).isPresent() && argMultimap.getPreamble().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubScoreCommand.MESSAGE_USAGE));
        }

        try {
            scoreToSub = SerenityParserUtil.parseScore(argMultimap.getValue(PREFIX_ADD_SCORE).get());
        } catch (NumberFormatException e) {
            throw new ParseException(Participation.MESSAGE_CONSTRAINTS);
        }

        if (scoreToSub == 0) {
            throw new ParseException(String.format(MESSAGE_SCORE_TO_SUB));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getValue(PREFIX_MATRIC).isPresent()) {
            studentName = SerenityParserUtil.parseStudentName(argMultimap.getValue(PREFIX_NAME).get());
            studentNumber = SerenityParserUtil.parseStudentNumber(argMultimap.getValue(PREFIX_MATRIC).get());
            student = Optional.ofNullable(new Student(studentName, studentNumber));
            return new SubScoreCommand(student.get(), scoreToSub);
        } else {
            try {
                index = SerenityParserUtil.parseIndex(argMultimap.getPreamble());
                return new SubScoreCommand(index, scoreToSub);
            } catch (NumberFormatException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubScoreCommand.MESSAGE_USAGE));
            }
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
     * ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
