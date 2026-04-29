package com.kurilo.array.factory.impl;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntArrayFactoryImplTest {

    private IntArrayFactoryImpl factory;

    @BeforeEach
    void setUp() {
        factory = new IntArrayFactoryImpl();
    }

    static Stream<Arguments> factoryDataProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3), new int[]{1, 2, 3}),
                Arguments.of(List.of(-5, 0, 10), new int[]{-5, 0, 10}),
                Arguments.of(List.of(), new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("factoryDataProvider")
    @DisplayName("Создание IntArray из списка Integer")
    void createFromListTest(List<Integer> inputList, int[] expectedArray) {
        IntArray result = factory.createFromList(inputList);

        assertAll(
                () -> assertEquals(expectedArray.length, result.getLength()),
                () -> assertArrayEquals(expectedArray, result.toArray())
        );
    }
}