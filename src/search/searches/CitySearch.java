package search.searches;

import search.Find;
import search.Person;

import java.util.List;

public class CitySearch implements ISearch {

    private final String value;
    private final Find find;

    private CitySearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static ISearch of(final String value, final Find find) {
        return new CitySearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getCity(), value))
                .toList();
    }
}
