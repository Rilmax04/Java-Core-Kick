package by.kurilo.array.validator.impl;

import by.kurilo.array.validator.DataValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegerValidatorTest {

    private final DataValidator validator = new IntegerValidator();

    @Test
    void isValidShouldReturnTrueForValidInteger() {
        assertTrue(validator.isValid("123"));
        assertTrue(validator.isValid("-456"));
        assertTrue(validator.isValid("0"));
    }

    @Test
    void isValidShouldReturnFalseForNonNumeric() {
        assertFalse(validator.isValid("12.5"));
        assertFalse(validator.isValid("abc"));
        assertFalse(validator.isValid("1a2"));
    }

    @Test
    void isValidShouldReturnFalseForNull() {
        assertFalse(validator.isValid(null));
    }

    @Test
    void isValidShouldReturnFalseForEmptyOrBlank() {
        assertFalse(validator.isValid(""));
        assertFalse(validator.isValid("   "));
    }

    @Test
    void isValidShouldReturnFalseForLoneMinus() {
        assertFalse(validator.isValid("-"));
    }

    @Test
    void isValidShouldTrimAndValidate() {
        assertTrue(validator.isValid("  789  "));
    }
}