package search.columns;

import search.findType.Find;
import search.domain.Person;

import java.util.List;

public class LastnameColumnSearch implements IColumnSearch {

    private final String value;
    private final Find find;

    private LastnameColumnSearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static IColumnSearch of(final String value, final Find find) {
        return new LastnameColumnSearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getLastname(), value))
                .toList();
    }
}
