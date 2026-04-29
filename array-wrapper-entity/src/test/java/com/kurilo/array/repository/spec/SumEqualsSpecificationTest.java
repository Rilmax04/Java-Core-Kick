package com.kurilo.array.repository.spec;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumEqualsSpecificationTest {

    static Stream<Arguments> sumProvider() {
        return Stream.of(
                Arguments.of(new int[]{10, 20, 30}, 60.0, true),
                Arguments.of(new int[]{1, 1, 1}, 5.0, false),
                Arguments.of(new int[]{-5, 5}, 0.0, true)
        );
    }

    @ParameterizedTest
    @MethodSource("sumProvider")
    @DisplayName("Проверка условия: сумма равна значению")
    void testSumEquals(int[] values, double threshold, boolean expected) {
        IntArray array = new IntArray(values);
        SumEqualsSpecification spec = new SumEqualsSpecification(threshold);
        assertEquals(expected, spec.test(array));
    }
}