package search.searches;

import search.Person;

import java.util.List;

public interface ISearch {

    List<Person> findAll(final List<Person> persons);
}
