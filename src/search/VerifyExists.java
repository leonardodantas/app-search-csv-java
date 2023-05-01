package search;

import java.util.List;

public class VerifyExists {

    private VerifyExists(){}

    private static final List<String> keys = List.of(
            "id",
            "firstname",
            "lastname",
            "email",
            "profession",
            "city",
            "details"
    );

    public static void keys(final List<Param> params) {
        params.forEach(param ->
                keys.stream()
                        .filter(key -> key.equalsIgnoreCase(param.getColumn()))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(String.format("%s not found", param))
                        ));
    }
}
