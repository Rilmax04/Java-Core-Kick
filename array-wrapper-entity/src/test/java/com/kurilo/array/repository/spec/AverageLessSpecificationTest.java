package com.kurilo.array.repository.spec;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageLessSpecificationTest {

    static Stream<Arguments> averageProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 5.0, true),
                Arguments.of(new int[]{10, 20}, 15.0, false),
                Arguments.of(new int[]{100}, 50.0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("averageProvider")
    @DisplayName("Проверка условия: среднее меньше порога")
    void testAverageLess(int[] values, double threshold, boolean expected) {
        IntArray array = new IntArray(values);
        AverageLessSpecification spec = new AverageLessSpecification(threshold);
        assertEquals(expected, spec.test(array));
    }
}