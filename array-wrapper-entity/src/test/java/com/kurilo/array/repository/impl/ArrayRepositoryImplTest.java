package com.kurilo.array.repository.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.repository.spec.SizeGreaterSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryImplTest {
    private ArrayRepositoryImpl repository;
    private final int[] smallArray = {1, 2};
    private final int[] bigArray = {1, 2, 3, 4, 5};

    @BeforeEach
    void setUp() {
        repository = ArrayRepositoryImpl.getInstance();
    }

    @Test
    @DisplayName("Добавление и удаление массива")
    void addRemoveArrayTest() {
        IntArray array = new IntArray(smallArray);

        assertAll("Операции с репозиторием",
                () -> assertDoesNotThrow(() -> repository.addArray(array)),
                () -> assertDoesNotThrow(() -> repository.removeArray(array))
        );
    }

    static Stream<Arguments> queryProvider() {
        return Stream.of(
                Arguments.of(3, 1),
                Arguments.of(10, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("queryProvider")
    @DisplayName("Запросы к репозиторию по спецификации")
    void queryArraysTest(double threshold, int expectedSize) {
        IntArray a1 = new IntArray(smallArray);
        IntArray a2 = new IntArray(bigArray);
        repository.addArray(a1);
        repository.addArray(a2);

        List<IntArray> result = repository.queryArrays(new SizeGreaterSpecification(threshold));

        assertEquals(expectedSize, result.size());
    }
}