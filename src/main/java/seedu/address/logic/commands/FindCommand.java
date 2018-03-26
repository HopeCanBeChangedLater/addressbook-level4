package seedu.address.logic.commands;

import static seedu.address.model.tutee.TuteeSortUtil.CATEGORY_EDUCATION_LEVEL;
import static seedu.address.model.tutee.TuteeSortUtil.CATEGORY_GRADE;
import static seedu.address.model.tutee.TuteeSortUtil.CATEGORY_MONTH;
import static seedu.address.model.tutee.TuteeSortUtil.CATEGORY_NAME;
import static seedu.address.model.tutee.TuteeSortUtil.CATEGORY_SCHOOL;
import static seedu.address.model.tutee.TuteeSortUtil.CATEGORY_SUBJECT;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tutee.EducationLevelContainsKeywordsPredicate;
import seedu.address.model.tutee.GradeContainsKeywordsPredicate;
import seedu.address.model.tutee.SchoolContainsKeywordsPredicate;
import seedu.address.model.tutee.SubjectContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose filter category contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_ALIAS = "f";

    public static final String MESSAGE_SUCCESS = "Find is successful.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": lists all person or tasks that suit the specified category\n"
            + "Parameters: filter_category keyword\n"
            + "Choice of filter_categories: "
            + CATEGORY_NAME + ", "
            + CATEGORY_MONTH + ", "
            + CATEGORY_EDUCATION_LEVEL + ", "
            + CATEGORY_GRADE + ", "
            + CATEGORY_SCHOOL + ", "
            + CATEGORY_SUBJECT + "\n"
            + "Example: " + COMMAND_WORD + " " + CATEGORY_GRADE + " A";

    private final String category;
    private final String[] keywords;
    private Predicate<Person> predicate;

    public FindCommand(String category, String[] keywords) {
        this.category = category;
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute() {
        switch (category) {
        case CATEGORY_NAME:
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
            model.updateFilteredPersonList(predicate);
            break;
        case CATEGORY_MONTH:
            break;
        case CATEGORY_EDUCATION_LEVEL:
            predicate = new EducationLevelContainsKeywordsPredicate(Arrays.asList(keywords));
            model.updateFilteredPersonList(predicate);
            break;
        case CATEGORY_GRADE:
            predicate = new GradeContainsKeywordsPredicate(Arrays.asList(keywords));
            model.updateFilteredPersonList(predicate);
            break;
        case CATEGORY_SCHOOL:
            predicate = new SchoolContainsKeywordsPredicate(Arrays.asList(keywords));
            model.updateFilteredPersonList(predicate);
            break;
        case CATEGORY_SUBJECT:
            predicate = new SubjectContainsKeywordsPredicate(Arrays.asList(keywords));
            model.updateFilteredPersonList(predicate);
            break;
        default:
            // invalid category should be detected in parser
            assert (false);
        }
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }
}
