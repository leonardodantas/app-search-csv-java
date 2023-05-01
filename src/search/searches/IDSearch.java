package search.searches;

import search.Find;
import search.Person;

import java.util.List;
import java.util.stream.Collectors;

public class IDSearch implements ISearch {

    private final String value;
    private final Find find;

    private IDSearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static ISearch of(final String value, final Find find) {
        return new IDSearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getId(), value))
                .collect(Collectors.toList());
    }
}
