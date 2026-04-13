package by.kurilo.array.parser;

import java.util.Optional;

public interface NumberParser<T extends Number> {

    Optional<T> parse (String token);
}
