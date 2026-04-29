package com.kurilo.array.repository.spec;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.service.impl.ArrayStatisticsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class SumEqualsSpecification implements Predicate<IntArray> {
    private static final Logger logger = LogManager.getLogger();
    private final double threshold;

    public SumEqualsSpecification(double threshold) {
        this.threshold = threshold;
        logger.debug("SumEqualsSpecification created with threshold: {}", threshold);
    }

    @Override
    public boolean test(IntArray array) {

        ArrayStatisticsServiceImpl arrayStatistic = new ArrayStatisticsServiceImpl();

        double sum = arrayStatistic.sum(array).orElse(0);

        return sum == threshold;
    }
}
