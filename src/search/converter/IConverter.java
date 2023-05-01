package search.converter;

public interface IConverter<T,U>{

    U convert(T object);
}
