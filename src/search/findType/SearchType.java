package search.findType;

import search.columns.*;

public enum SearchType {
    ID() {
        @Override
        public IColumnSearch execute(final String value, final Find find) {
            return IDColumnSearch.of(value, find);
        }
    }, FIRSTNAME() {
        @Override
        public IColumnSearch execute(final String value, final Find find) {
            return FirstNameColumnSearch.of(value, find);
        }
    }, LASTNAME() {
        @Override
        public IColumnSearch execute(final String value, final Find find) {
            return LastnameColumnSearch.of(value, find);
        }
    }, EMAIL() {
        @Override
        public IColumnSearch execute(final String value, final Find find) {
            return EmailColumnSearch.of(value, find);
        }
    }, PROFESSION() {
        @Override
        public IColumnSearch execute(final String value, final Find find) {
            return ProfessionColumnSearch.of(value, find);
        }
    }, CITY() {
        @Override
        public IColumnSearch execute(final String value, final Find find) {
            return CityColumnSearch.of(value, find);
        }
    }, DETAILS() {
        @Override
        public IColumnSearch execute(final String value, final Find find) {
            return DetailsColumnSearch.of(value, find);
        }
    };

    public abstract IColumnSearch execute(final String value, final Find find);
}
