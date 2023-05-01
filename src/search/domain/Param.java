package search.domain;

import search.findType.Find;

import java.util.Locale;

public class Param {

    private final String column;
    private final String value;
    private final Find find;

    private Param(final String column, final String value, final Find find) {
        this.column = column;
        this.value = value;
        this.find = find;
    }

    public static Param of(final String column, final String value) {
        return new Param(column, value, Find.EQUALS);
    }

    public static Param of(final String column, final String value, final Find find) {
        return new Param(column, value, find);
    }

    public String getColumn() {
        return column.toUpperCase(Locale.ROOT);
    }

    public String getValue() {
        return value;
    }

    public Find getFind() {
        return find;
    }
}
