package com.kurilo.array.service.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;
import com.kurilo.array.observer.impl.ArrayStatisticsObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArraySortingServiceImplTest {

    private ArraySortingServiceImpl sortingService;
    private ArrayStatisticsObserver observer;

    @BeforeEach
    void setUp() {
        sortingService = new ArraySortingServiceImpl();
        observer = new ArrayStatisticsObserver(); // Создаем наблюдателя
    }

    static Stream<Arguments> sortingDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{5, 1, 4, 2, 8}, new int[]{1, 2, 4, 5, 8}),
                Arguments.of(new int[]{8, 5, 4, 2, 1}, new int[]{1, 2, 4, 5, 8}),
                Arguments.of(new int[]{10, -5, 0}, new int[]{-5, 0, 10}),
                Arguments.of(new int[]{10}, new int[]{10}),
                Arguments.of(new int[]{}, new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("sortingDataProvider")
    @DisplayName("Тестирование bubbleSort с прикрепленным наблюдателем")
    void bubbleSortTest(int[] input, int[] expected) throws ArrayDataException {
        IntArray intArray = new IntArray(input);
        intArray.attach(observer); // Прикрепляем, чтобы setElement не падал

        sortingService.bubbleSort(intArray);

        assertAll("Результат пузырьковой сортировки",
                () -> assertArrayEquals(expected, intArray.toArray())
        );
    }

    @ParameterizedTest
    @MethodSource("sortingDataProvider")
    @DisplayName("Тестирование insertionSort с прикрепленным наблюдателем")
    void insertionSortTest(int[] input, int[] expected) {
        IntArray intArray = new IntArray(input);
        intArray.attach(observer); // Прикрепляем, чтобы notifyObserver() нашел объект

        sortingService.insertionSort(intArray);

        assertAll("Результат сортировки вставками",
                () -> assertArrayEquals(expected, intArray.toArray())
        );
    }
}