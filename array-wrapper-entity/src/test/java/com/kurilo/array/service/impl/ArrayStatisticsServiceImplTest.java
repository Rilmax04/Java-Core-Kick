package com.kurilo.array.service.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsServiceImplTest {

    private ArrayStatisticsServiceImpl service;
    private static final int[] sample = {10, -5, 20, 0, 15};

    @BeforeEach
    void setUp() {
        service = new ArrayStatisticsServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("provideStatisticsCases")
    @DisplayName("Статистики: min, max, sum, average")
    void statistics_correctValues(IntArray array, int expectedMin, int expectedMax, int expectedSum, double expectedAvg)  {
        assertAll("Statistics",
                () -> assertEquals(expectedMin, service.findMin(array).orElseThrow()),
                () -> assertEquals(expectedMax, service.findMax(array).orElseThrow()),
                () -> assertEquals(expectedSum, service.sum(array).orElseThrow()),
                () -> assertEquals(expectedAvg, service.average(array).orElseThrow(), 0.001)
        );
    }

    private static Stream<Arguments> provideStatisticsCases() {
        return Stream.of(
                Arguments.of(new IntArray(sample), -5, 20, 40, 8.0),
                Arguments.of(new IntArray(5, 5, 5), 5, 5, 15, 5.0),
                Arguments.of(new IntArray(-10, -20, -5), -20, -5, -35, -11.667),
                Arguments.of(new IntArray(42), 42, 42, 42, 42.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideEmptyCases")
    @DisplayName("Пустой и null массив")
    void statistics_emptyOrNull_returnsEmpty(IntArray array) {
        assertAll("Empty cases",
                () -> assertTrue(service.findMin(array).isEmpty()),
                () -> assertTrue(service.findMax(array).isEmpty()),
                () -> assertTrue(service.sum(array).isEmpty()),
                () -> assertTrue(service.average(array).isEmpty())
        );
    }

    private static Stream<Arguments> provideEmptyCases() {
        return Stream.of(
                Arguments.of((IntArray) null),
                Arguments.of(new IntArray())
        );
    }

    @Test
    @DisplayName("Переполнение суммы")
    void sum_overflow_returnsEmpty() {
        IntArray arr = new IntArray(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertTrue(service.sum(arr).isEmpty());
    }

    @Test
    @DisplayName("Среднее с дробной частью")
    void average_fractionalValue_precision() {
        IntArray arr = new IntArray(1, 2, 3);
        OptionalDouble avg = service.average(arr);
        assertTrue(avg.isPresent());
        assertEquals(2.0, avg.getAsDouble(), 0.001);
    }
}