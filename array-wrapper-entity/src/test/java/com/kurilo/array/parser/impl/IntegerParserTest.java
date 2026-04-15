package by.kurilo.array.parser.impl;

import by.kurilo.array.parser.NumberParser;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class IntegerParserTest {

    private final NumberParser<Integer> parser = new IntegerParser();

    @Test
    void parseShouldReturnOptionalWithIntegerForValidNumber() {
        Optional<Integer> result = parser.parse("123");
        assertTrue(result.isPresent());
        assertEquals(123, result.get());
    }

    @Test
    void parseShouldHandleNegativeNumbers() {
        Optional<Integer> result = parser.parse("-456");
        assertTrue(result.isPresent());
        assertEquals(-456, result.get());
    }

    @Test
    void parseShouldReturnEmptyForNonNumericString() {
        Optional<Integer> result = parser.parse("abc");
        assertFalse(result.isPresent());
    }

    @Test
    void parseShouldReturnEmptyForNull() {
        Optional<Integer> result = parser.parse(null);
        assertFalse(result.isPresent());
    }

    @Test
    void parseShouldReturnEmptyForBlankString() {
        Optional<Integer> result = parser.parse("   ");
        assertFalse(result.isPresent());
    }

    @Test
    void parseShouldTrimWhitespace() {
        Optional<Integer> result = parser.parse("  42  ");
        assertTrue(result.isPresent());
        assertEquals(42, result.get());
    }

    @Test
    void parseShouldReturnEmptyForOverflowLongNumber() {
        Optional<Integer> result = parser.parse("9999999999999");
        assertFalse(result.isPresent());
    }
}