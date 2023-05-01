package search;

import search.columns.IColumnSearch;
import search.converter.PersonConverter;
import search.domain.Param;
import search.domain.Person;
import search.findType.SearchType;
import search.utils.VerifyExistsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchCSV {

    private final List<Person> persons;
    private final List<IColumnSearch> searches;
    private static final PersonConverter converter = new PersonConverter();

    private SearchCSV(final List<Person> persons, final List<IColumnSearch> searches) {
        this.persons = persons;
        this.searches = searches;
    }

    public static SearchCSV of(final String file, final List<Param> params) {
        VerifyExistsUtils.verify(params);
        final var persons = converter.convert(file);
        final var searches = getSearches(params);
        return new SearchCSV(persons, searches);
    }

    public List<Person> findAll() {
        return recursiveSearchAll(new ArrayList<>(searches), persons);
    }

    public Person findOne() {
        final var persons = findAll();
        return persons.stream().findFirst().orElse(new Person());
    }

    private static List<IColumnSearch> getSearches(final List<Param> params) {
        return params.stream()
                .map(param -> {
                    final var searchType = SearchType.valueOf(param.getColumn());
                    return searchType.execute(param.getValue(), param.getFind());
                })
                .collect(Collectors.toList());
    }

    private static List<Person> recursiveSearchAll(final List<IColumnSearch> searches, final List<Person> persons) {
        final var search = searches.stream().findFirst().orElseThrow();
        final var personsFound = search.findAll(persons);
        searches.remove(search);

        if (searches.isEmpty() || personsFound.isEmpty()) {
            return personsFound;
        }

        return recursiveSearchAll(searches, personsFound);
    }

}
