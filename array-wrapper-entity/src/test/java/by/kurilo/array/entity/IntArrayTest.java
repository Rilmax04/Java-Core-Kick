package by.kurilo.array.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntArrayTest {

    @Test
    void constructorShouldCreateDefensiveCopy() {
        int[] original = {1, 2, 3};
        IntArray array = new IntArray(original);
        original[0] = 100;
        assertEquals(1, array.get(0));
    }

    @Test
    void toArrayShouldReturnDefensiveCopy() {
        IntArray array = new IntArray(5, 6, 7);
        int[] returned = array.toArray();
        returned[0] = 999;
        assertEquals(5, array.get(0));
    }

    @Test
    void lengthShouldReturnCorrectSize() {
        IntArray empty = new IntArray();
        IntArray nonEmpty = new IntArray(1, 2);
        assertEquals(0, empty.length());
        assertEquals(2, nonEmpty.length());
    }

    @Test
    void getShouldReturnElementAtIndex() {
        IntArray array = new IntArray(10, 20, 30);
        int value = array.get(1);
        assertEquals(20, value);
    }

    @Test
    void getShouldThrowIndexOutOfBounds() {
        IntArray array = new IntArray(1, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(5));
    }

    @Test
    void setShouldModifyElement() {
        IntArray array = new IntArray(1, 2, 3);
        array.set(1, 99);
        assertEquals(99, array.get(1));
    }

    @Test
    void toStringShouldContainElements() {
        IntArray array = new IntArray(42, 55);
        String str = array.toString();
        assertTrue(str.contains("42"));
        assertTrue(str.contains("55"));
    }
}