package com.kurilo.array.builder;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class IntArrayBuilderTest {

    @ParameterizedTest
    @CsvSource({
            "5, 0, 5",
            "10, 1, 10",
            "15, 2, 15"
    })
    void buildShouldCreateArrayWithAddedValues(int value, int index, int expected) {
        IntArrayBuilder builder = new IntArrayBuilder();
        builder.add(5).add(10).add(15);
        IntArray array = builder.build();

        assertAll("IntArray content",
                () -> assertEquals(3, array.length()),
                () -> assertEquals(expected, array.get(index))
        );
    }

    @Test
    void addAllShouldCopyFromAnotherIntArray() {
        IntArray source = new IntArray(7, 8, 9);
        IntArrayBuilder builder = new IntArrayBuilder();
        builder.add(1).addAll(source);
        IntArray array = builder.build();

        assertAll("IntArray after addAll",
                () -> assertEquals(4, array.length()),
                () -> assertEquals(1, array.get(0)),
                () -> assertEquals(7, array.get(1)),
                () -> assertEquals(8, array.get(2)),
                () -> assertEquals(9, array.get(3))
        );
    }

    @Test
    void buildWithNoValuesShouldReturnEmptyArray() {
        IntArrayBuilder builder = new IntArrayBuilder();
        IntArray array = builder.build();
        assertEquals(0, array.length());
    }
}