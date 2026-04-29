package com.kurilo.array.comparator;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FirstElementComparatorTest {
    private FirstElementComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new FirstElementComparator();
    }

    static Stream<Arguments> firstElementProvider() {
        return Stream.of(
                Arguments.of(new int[]{0, 10}, new int[]{0, 20}, -1),
                Arguments.of(new int[]{99, 5}, new int[]{0, 5}, 0),
                Arguments.of(new int[]{0, 30}, new int[]{0, 10}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("firstElementProvider")
    @DisplayName("Сравнение по элементу с индексом 1")
    void compareFirstElementTest(int[] first, int[] second, int expectedSign) {
        IntArray a = new IntArray(first);
        IntArray b = new IntArray(second);
        int result = comparator.compare(a, b);

        assertAll("Проверка сравнения элементов",
                () -> {
                    if (expectedSign == 0) assertEquals(0, result);
                    else assertTrue(expectedSign > 0 ? result > 0 : result < 0);
                }
        );
    }
}