package search.searches;

import search.Find;
import search.Person;

import java.util.List;
import java.util.stream.Collectors;

public class EmailSearch implements ISearch {

    private final String value;
    private final Find find;

    private EmailSearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static ISearch of(final String value, final Find find) {
        return new EmailSearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getEmail(), value))
                .collect(Collectors.toList());
    }
}
