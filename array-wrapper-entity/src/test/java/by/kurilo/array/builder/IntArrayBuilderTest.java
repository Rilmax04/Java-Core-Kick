package by.kurilo.array.builder;

import by.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntArrayBuilderTest {

    @Test
    void buildShouldCreateArrayWithAddedValues() {
        IntArrayBuilder builder = new IntArrayBuilder();
        builder.add(5).add(10).add(15);
        IntArray array = builder.build();
        assertEquals(3, array.length());
        assertEquals(5, array.get(0));
        assertEquals(10, array.get(1));
        assertEquals(15, array.get(2));
    }

    @Test
    void addAllShouldCopyFromAnotherIntArray() {
        IntArray source = new IntArray(7, 8, 9);
        IntArrayBuilder builder = new IntArrayBuilder();
        builder.add(1).addAll(source);
        IntArray array = builder.build();
        assertEquals(4, array.length());
        assertEquals(1, array.get(0));
        assertEquals(7, array.get(1));
        assertEquals(8, array.get(2));
        assertEquals(9, array.get(3));
    }

    @Test
    void buildWithNoValuesShouldReturnEmptyArray() {
        IntArrayBuilder builder = new IntArrayBuilder();
        IntArray array = builder.build();
        assertEquals(0, array.length());
    }
}