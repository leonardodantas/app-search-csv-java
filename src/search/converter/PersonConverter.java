package search.converter;

import search.domain.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonConverter implements IConverter<String, List<Person>> {

    @Override
    public List<Person> convert(final String file) {
        try (final var lines = new BufferedReader(new FileReader(file)).lines()) {
            return lines.skip(1).map(line -> {
                final var values = line.split(",");
                return Person.builder()
                        .id(values[0])
                        .firstname(values[1])
                        .lastname(values[2])
                        .email(values[3])
                        .profession(values[4])
                        .city(values[5])
                        .details(values[6])
                        .build();
            }).collect(Collectors.toList());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
