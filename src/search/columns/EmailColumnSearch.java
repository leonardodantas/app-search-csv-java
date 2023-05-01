package search.columns;

import search.findType.Find;
import search.domain.Person;

import java.util.List;
import java.util.stream.Collectors;

public class EmailColumnSearch implements IColumnSearch {

    private final String value;
    private final Find find;

    private EmailColumnSearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static IColumnSearch of(final String value, final Find find) {
        return new EmailColumnSearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getEmail(), value))
                .collect(Collectors.toList());
    }
}
