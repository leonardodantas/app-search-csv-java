package search;

public interface IConverter<T,U>{

    U convert(T object);
}
