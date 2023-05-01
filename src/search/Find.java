package search;

import java.util.Locale;

public enum Find {

    EQUALS {
        @Override
        public boolean execute(final String valueParam, final String valueCSV) {
            return valueParam.equalsIgnoreCase(valueCSV);
        }
    }, CONTAINS {
        @Override
        public boolean execute(final String valueParam, final String valueCSV) {
            return valueParam.toUpperCase(Locale.ROOT).contains(valueCSV.toUpperCase(Locale.ROOT));
        }
    };

    public abstract boolean execute(final String valueParam, final String valueCSV);

}
