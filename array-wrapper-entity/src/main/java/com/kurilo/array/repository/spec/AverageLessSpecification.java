package com.kurilo.array.repository.spec;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.service.impl.ArrayStatisticsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class AverageLessSpecification implements Predicate<IntArray> {
    private static final Logger logger = LogManager.getLogger();
    private final double threshold;

    public AverageLessSpecification(double threshold) {
        this.threshold = threshold;
        logger.debug("AverageLessSpecification created with threshold: {}", threshold);
    }

    @Override
    public boolean test(IntArray array) {

        ArrayStatisticsServiceImpl arrayStatistic = new ArrayStatisticsServiceImpl();

        double average = arrayStatistic.average(array).orElse(0);

        return average < threshold;
    }
}
