package com.kurilo.array.validator.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerValidatorTest {
    private IntegerValidator validator;

    @BeforeEach
    void setUp() {
        validator = new IntegerValidator();
    }

    static Stream<Arguments> tokenProvider() {
        return Stream.of(
                Arguments.of("123", true),
                Arguments.of("-50", true),
                Arguments.of("0", true),
                Arguments.of("12.3", false),
                Arguments.of("abc", false),
                Arguments.of("", false),
                Arguments.of(null, false),
                Arguments.of("2147483648", false)
        );
    }

    @ParameterizedTest
    @MethodSource("tokenProvider")
    @DisplayName("Валидация строковых токенов")
    void isValidTest(String token, boolean expected) {
        boolean actual = validator.isValid(token);

        assertAll("Проверка валидности токена",
                () -> assertEquals(expected, actual, "Ошибка валидации для токена: " + token)
        );
    }
}