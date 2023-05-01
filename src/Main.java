import search.Find;
import search.Param;
import search.SearchCSV;

import java.util.List;

public class Main {

    public static void main(final String[] args) {
        final var file = "/home/leo-dantas/Downloads/person.csv";

        final var params = List.of(
                Param.of("profession", "des", Find.EQUALS)
        );

        final var searchCSV = SearchCSV.of(file, params);

        final var results = searchCSV.findAll();
        System.out.println(results.size());

        final var result = searchCSV.findOne();
        System.out.println(result.getFirstname());

    }
}
