package com.kurilo.array.parser.impl;

import com.kurilo.array.exception.ArrayDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerParserTest {

    private IntegerParser parser;

    @BeforeEach
    void setUp() {
        parser = new IntegerParser();
    }

    static Stream<Arguments> validLinesProvider() {
        return Stream.of(
                Arguments.of("1, 2, 3", new int[]{1, 2, 3}),
                Arguments.of("10;20-30 40", new int[]{10, 20, 30, 40}),
                Arguments.of("  5   6   7  ", new int[]{5, 6, 7}),
                Arguments.of("1, invalid, 2", new int[]{1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("validLinesProvider")
    @DisplayName("Парсинг валидных строк с различными разделителями")
    void parseToArrayValidDataTest(String input, int[] expected) throws ArrayDataException {
        int[] actual = parser.parseToArray(input);

        assertAll("Проверка результата парсинга",
                () -> assertEquals(expected.length, actual.length, "Длина массива не совпадает"),
                () -> assertArrayEquals(expected, actual, "Содержимое массива не совпадает")
        );
    }

    @Test
    @DisplayName("Исключение при пустой строке")
    void parseToArrayEmptyLineExceptionTest() {
        assertThrows(ArrayDataException.class, () -> parser.parseToArray("   "));
    }
}