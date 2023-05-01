package search.columns;

import search.domain.Person;

import java.util.List;

public interface IColumnSearch {

    List<Person> findAll(final List<Person> persons);
}
