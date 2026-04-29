package com.kurilo.array.service.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.service.ArrayStatisticsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ArrayStatisticsServiceImpl implements ArrayStatisticsService {
    private static final Logger logger = LogManager.getLogger(ArrayStatisticsServiceImpl.class);

    @Override
    public OptionalInt findMin(IntArray array)  {
        if (array == null || array.getLength() == 0) {
            logger.debug("findMin: array is null or empty");
            return OptionalInt.empty();
        }
        int min = array.getElement(0);
        for (int i = 1; i < array.getLength(); i++) {
            int val = array.getElement(i);
            if (val < min) {
                min = val;
            }
        }
        logger.debug("findMin: {}", min);
        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt findMax(IntArray array) {
        if (array == null || array.getLength() == 0) {
            logger.debug("findMax: array is null or empty");
            return OptionalInt.empty();
        }
        int max = array.getElement(0);
        for (int i = 1; i < array.getLength(); i++) {
            int val = array.getElement(i);
            if (val > max) {
                max = val;
            }
        }
        logger.debug("findMax: {}", max);
        return OptionalInt.of(max);
    }

    @Override
    public OptionalInt sum(IntArray array) {
        if (array == null || array.getLength() == 0) {
            logger.debug("sum: array is null or empty");
            return OptionalInt.empty();
        }
        long sum = 0;
        for (int i = 0; i < array.getLength(); i++) {
            sum += array.getElement(i);
        }
        if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
            logger.warn("sum: overflow detected");
            return OptionalInt.empty();
        }
        logger.debug("sum: {}", (int) sum);
        return OptionalInt.of((int) sum);
    }

    @Override
    public OptionalDouble average(IntArray array)  {
        if (array == null || array.getLength() == 0) {
            logger.debug("average: array is null or empty");
            return OptionalDouble.empty();
        }
        long sum = 0;
        for (int i = 0; i < array.getLength(); i++) {
            sum += array.getElement(i);
        }
        double avg = (double) sum / array.getLength();
        logger.debug("average: {}", avg);
        return OptionalDouble.of(avg);
    }
}