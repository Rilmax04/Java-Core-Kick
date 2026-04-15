package by.kurilo.array.service.impl;

import by.kurilo.array.entity.IntArray;
import by.kurilo.array.service.ArraySortingService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArraySortingServiceImplTest {

    private final ArraySortingService service = new ArraySortingServiceImpl();

    private static final int[] UNSORTED = {3, 1, 4, 1, 5};
    private static final int[] SORTED = {1, 1, 3, 4, 5};

    @Test
    void bubbleSortShouldSortAscending() {
        IntArray array = new IntArray(UNSORTED);
        service.bubbleSort(array);
        assertArrayEquals(SORTED, array.toArray());
    }

    @Test
    void bubbleSortOnEmptyArrayShouldDoNothing() {
        IntArray empty = new IntArray();
        service.bubbleSort(empty);
        assertEquals(0, empty.length());
    }

    @Test
    void bubbleSortOnSingleElementShouldDoNothing() {
        IntArray single = new IntArray(42);
        service.bubbleSort(single);
        assertEquals(42, single.get(0));
    }

    @Test
    void quickSortShouldSortAscending() {
        IntArray array = new IntArray(9, 2, 5, 1, 7);
        service.quickSort(array);
        assertArrayEquals(new int[]{1, 2, 5, 7, 9}, array.toArray());
    }

    @Test
    void quickSortOnEmptyArrayShouldDoNothing() {
        IntArray empty = new IntArray();
        service.quickSort(empty);
        assertEquals(0, empty.length());
    }

    @Test
    void quickSortOnSingleElementShouldDoNothing() {
        IntArray single = new IntArray(100);
        service.quickSort(single);
        assertEquals(100, single.get(0));
    }

    @Test
    void bothSortsShouldProduceSameResult() {
        int[] original = {5, 3, 8, 1, 2, 7};
        IntArray array1 = new IntArray(original);
        IntArray array2 = new IntArray(original);
        service.bubbleSort(array1);
        service.quickSort(array2);
        assertArrayEquals(array1.toArray(), array2.toArray());
    }
}