package search.columns;

import search.findType.Find;
import search.domain.Person;

import java.util.List;
import java.util.stream.Collectors;

public class IDColumnSearch implements IColumnSearch {

    private final String value;
    private final Find find;

    private IDColumnSearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static IColumnSearch of(final String value, final Find find) {
        return new IDColumnSearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getId(), value))
                .collect(Collectors.toList());
    }
}
