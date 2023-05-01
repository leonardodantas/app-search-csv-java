package search.utils;

import search.domain.Param;
import search.exceptions.ParamNotFoundException;

import java.util.List;
import java.util.Set;

public class VerifyExistsUtils {

    private VerifyExistsUtils() {
    }

    private static final Set<String> keys = Set.of(
            "id",
            "firstname",
            "lastname",
            "email",
            "profession",
            "city",
            "details"
    );

    public static void verify(final List<Param> params) {
        params.forEach(param ->
                keys.stream()
                        .filter(key -> key.equalsIgnoreCase(param.getColumn()))
                        .findFirst()
                        .orElseThrow(() ->
                                new ParamNotFoundException(String.format("%s not found", param))
                        ));
    }
}
