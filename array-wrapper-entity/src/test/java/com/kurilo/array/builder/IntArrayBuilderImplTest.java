package com.kurilo.array.builder;

import com.kurilo.array.builder.impl.IntArrayBuilderImpl;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class IntArrayBuilderImplTest {

    @ParameterizedTest
    @CsvSource({
            "5, 0, 5",
            "10, 1, 10",
            "15, 2, 15"
    })
    void buildShouldCreateArrayWithAddedValues(int value, int index, int expected) {
        IntArrayBuilderImpl builder = new IntArrayBuilderImpl();
        builder.add(5).add(10).add(15);
        IntArray array = builder.build();

        assertAll("IntArray content",
                () -> assertEquals(3, array.getLength()),
                () -> assertEquals(expected, array.getElement(index))
        );
    }

    @Test
    void addAllShouldCopyFromAnotherIntArray() throws ArrayDataException {
        IntArray source = new IntArray(7, 8, 9);
        IntArrayBuilderImpl builder = new IntArrayBuilderImpl();
        builder.add(1).addAll(source);
        IntArray array = builder.build();

        assertAll("IntArray after addAll",
                () -> assertEquals(4, array.getLength()),
                () -> assertEquals(1, array.getElement(0)),
                () -> assertEquals(7, array.getElement(1)),
                () -> assertEquals(8, array.getElement(2)),
                () -> assertEquals(9, array.getElement(3))
        );
    }

    @Test
    void buildWithNoValuesShouldReturnEmptyArray() {
        IntArrayBuilderImpl builder = new IntArrayBuilderImpl();
        IntArray array = builder.build();
        assertEquals(0, array.getLength());
    }
}