package search.columns;

import search.findType.Find;
import search.domain.Person;

import java.util.List;

public class CityColumnSearch implements IColumnSearch {

    private final String value;
    private final Find find;

    private CityColumnSearch(final String value, final Find find) {
        this.value = value;
        this.find = find;
    }

    public static IColumnSearch of(final String value, final Find find) {
        return new CityColumnSearch(value, find);
    }

    @Override
    public List<Person> findAll(final List<Person> persons) {
        return persons.stream()
                .filter(person -> find.execute(person.getCity(), value))
                .toList();
    }
}
