package by.kurilo.array.factory;

import by.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IntArrayFactoryTest {

    private static final List<Integer> NUMBERS = Arrays.asList(10, 20, 30, 40);
    private static final int[] EXPECTED_ARRAY = {10, 20, 30, 40};
    private static final List<Integer> EMPTY_LIST = new ArrayList<>();

    private final IntArrayFactory factory = new IntArrayFactory();

    @Test
    void createFromListShouldBuildIntArrayWithCorrectElements() {
        List<Integer> numbers = new ArrayList<>(NUMBERS);
        IntArray array = factory.createFromList(numbers);
        assertArrayEquals(EXPECTED_ARRAY, array.toArray());
    }

    @Test
    void createFromListShouldReturnEmptyArrayWhenListIsEmpty() {
        IntArray array = factory.createFromList(EMPTY_LIST);
        assertEquals(0, array.length());
    }

    @Test
    void createFromListShouldNotModifyOriginalList() {
        List<Integer> original = new ArrayList<>(NUMBERS);
        List<Integer> copy = new ArrayList<>(original);
        factory.createFromList(original);
        assertEquals(copy, original);
    }

    @Test
    void createFromListShouldCreateDefensiveCopyOfListElements() {
        List<Integer> numbers = new ArrayList<>(NUMBERS);
        IntArray array = factory.createFromList(numbers);
        numbers.set(0, 999);
        assertEquals(10, array.get(0));
    }

    @Test
    void createFromListShouldPreserveOrderOfElements() {
        List<Integer> numbers = Arrays.asList(5, 1, 9, 3);
        IntArray array = factory.createFromList(numbers);
        assertArrayEquals(new int[]{5, 1, 9, 3}, array.toArray());
    }
}