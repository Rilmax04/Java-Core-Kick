package by.kurilo.array.service.impl;

import by.kurilo.array.entity.IntArray;
import by.kurilo.array.service.ArrayStatisticsService;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsServiceImplTest {

    private final ArrayStatisticsService service = new ArrayStatisticsServiceImpl();

    private static final IntArray NON_EMPTY_ARRAY = new IntArray(5, 2, 8, 1, 3);
    private static final IntArray EMPTY_ARRAY = new IntArray();
    private static final IntArray SUM_ARRAY = new IntArray(1, 2, 3, 4);
    private static final IntArray AVG_ARRAY = new IntArray(2, 4, 6);

    @Test
    void findMinShouldReturnSmallestValue() {
        Optional<Integer> min = service.findMin(NON_EMPTY_ARRAY);
        assertTrue(min.isPresent());
        assertEquals(1, min.get());
    }

    @Test
    void findMinOnEmptyArrayShouldReturnEmpty() {
        Optional<Integer> min = service.findMin(EMPTY_ARRAY);
        assertFalse(min.isPresent());
    }

    @Test
    void findMaxShouldReturnLargestValue() {
        Optional<Integer> max = service.findMax(NON_EMPTY_ARRAY);
        assertTrue(max.isPresent());
        assertEquals(8, max.get());
    }

    @Test
    void findMaxOnEmptyArrayShouldReturnEmpty() {
        Optional<Integer> max = service.findMax(EMPTY_ARRAY);
        assertFalse(max.isPresent());
    }

    @Test
    void sumShouldReturnTotalOfElements() {
        Optional<Integer> sum = service.sum(SUM_ARRAY);
        assertTrue(sum.isPresent());
        assertEquals(10, sum.get());
    }

    @Test
    void sumOnEmptyArrayShouldReturnEmpty() {
        Optional<Integer> sum = service.sum(EMPTY_ARRAY);
        assertFalse(sum.isPresent());
    }

    @Test
    void averageShouldReturnMeanValue() {
        Optional<Double> avg = service.average(AVG_ARRAY);
        assertTrue(avg.isPresent());
        assertEquals(4.0, avg.get(), 0.0001);
    }

    @Test
    void averageOnEmptyArrayShouldReturnEmpty() {
        Optional<Double> avg = service.average(EMPTY_ARRAY);
        assertFalse(avg.isPresent());
    }
}