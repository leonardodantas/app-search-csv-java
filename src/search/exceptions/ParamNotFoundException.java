package search.exceptions;

public class ParamNotFoundException extends RuntimeException {
    public ParamNotFoundException(final String message) {
        super(message);
    }
}
