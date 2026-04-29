package com.kurilo.array.repository.spec;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SizeGreaterSpecificationTest {

    static Stream<Arguments> sizeProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 2.0, true),
                Arguments.of(new int[]{1, 2, 3}, 3.0, false),
                Arguments.of(new int[]{}, 0.0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("sizeProvider")
    @DisplayName("Проверка условия: размер больше порога")
    void testSizeGreater(int[] values, double threshold, boolean expected) {
        IntArray array = new IntArray(values);
        SizeGreaterSpecification spec = new SizeGreaterSpecification(threshold);
        assertEquals(expected, spec.test(array));
    }
}