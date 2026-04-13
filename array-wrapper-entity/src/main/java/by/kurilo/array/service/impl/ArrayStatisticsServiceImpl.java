package by.kurilo.array.service.impl;

import by.kurilo.array.entity.IntArray;
import by.kurilo.array.service.ArrayStatisticsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;
import java.util.stream.IntStream;

public class ArrayStatisticsServiceImpl implements ArrayStatisticsService {
    private static final Logger LOGGER = LogManager.getLogger(ArrayStatisticsServiceImpl.class);

    @Override
    public Optional<Integer> findMin(IntArray array) {
        if (array.length() == 0) {
            LOGGER.debug("findMin called on empty array");
            return Optional.empty();
        }
        int min = IntStream.range(0, array.length())
                .map(array::get)
                .min()
                .orElseThrow(() -> new IllegalStateException("No min found in non-empty array"));
        return Optional.of(min);
    }

    @Override
    public Optional<Integer> findMax(IntArray array) {
        if (array.length() == 0) {
            LOGGER.debug("findMax called on empty array");
            return Optional.empty();
        }
        int max = IntStream.range(0, array.length())
                .map(array::get)
                .max()
                .orElseThrow(() -> new IllegalStateException("No max found in non-empty array"));
        return Optional.of(max);
    }

    @Override
    public Optional<Integer> sum(IntArray array) {
        if (array.length() == 0) {
            LOGGER.debug("sum called on empty array");
            return Optional.empty();
        }
        int sum = IntStream.range(0, array.length())
                .map(array::get)
                .sum();
        return Optional.of(sum);
    }

    @Override
    public Optional<Double> average(IntArray array) {
        if (array.length() == 0) {
            LOGGER.debug("average called on empty array");
            return Optional.empty();
        }
        double avg = IntStream.range(0, array.length())
                .map(array::get)
                .average()
                .orElseThrow(() -> new IllegalStateException("No average found in non-empty array"));
        return Optional.of(avg);
    }
}