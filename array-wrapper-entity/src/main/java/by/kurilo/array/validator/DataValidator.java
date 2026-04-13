package by.kurilo.array.validator;

@FunctionalInterface
public interface DataValidator {

    boolean isValid(String token);
}
