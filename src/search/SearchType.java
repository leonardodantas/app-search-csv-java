package search;

import search.searches.*;

public enum SearchType {
    ID() {
        @Override
        public ISearch execute(final String value, final Find find) {
            return IDSearch.of(value, find);
        }
    }, FIRSTNAME() {
        @Override
        public ISearch execute(final String value, final Find find) {
            return FirstNameSearch.of(value, find);
        }
    }, LASTNAME() {
        @Override
        public ISearch execute(final String value, final Find find) {
            return LastnameSearch.of(value, find);
        }
    }, EMAIL() {
        @Override
        public ISearch execute(final String value, final Find find) {
            return EmailSearch.of(value, find);
        }
    }, PROFESSION() {
        @Override
        public ISearch execute(final String value, final Find find) {
            return ProfessionSearch.of(value, find);
        }
    }, CITY() {
        @Override
        public ISearch execute(final String value, final Find find) {
            return CitySearch.of(value, find);
        }
    }, DETAILS() {
        @Override
        public ISearch execute(final String value, final Find find) {
            return DetailsSearch.of(value, find);
        }
    };

    public abstract ISearch execute(final String value, final Find find);
}
