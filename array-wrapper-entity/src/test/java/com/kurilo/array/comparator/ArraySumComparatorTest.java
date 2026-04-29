package com.kurilo.array.comparator;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArraySumComparatorTest {
    private ArraySumComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new ArraySumComparator();
    }

    static Stream<Arguments> sumComparisonProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{10, 20}, -1),
                Arguments.of(new int[]{5, 5}, new int[]{2, 8}, 0),
                Arguments.of(new int[]{100}, new int[]{1, 2}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("sumComparisonProvider")
    @DisplayName("Сравнение массивов по сумме элементов")
    void compareSumTest(int[] first, int[] second, int expectedSign) {
        IntArray a = new IntArray(first);
        IntArray b = new IntArray(second);
        int result = comparator.compare(a, b);

        if (expectedSign == 0) {
            assertEquals(0, result);
        } else {
            assertTrue(expectedSign > 0 ? result > 0 : result < 0);
        }
    }
}