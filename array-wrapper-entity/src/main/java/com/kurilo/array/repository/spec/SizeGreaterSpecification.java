package com.kurilo.array.repository.spec;

import com.kurilo.array.entity.IntArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class SizeGreaterSpecification implements Predicate<IntArray> {
    private static final Logger logger = LogManager.getLogger();
    private final double threshold;

    public SizeGreaterSpecification(double threshold) {
        this.threshold = threshold;
        logger.debug("SizeGreaterSpecification created with threshold: {}", threshold);
    }

    @Override
    public boolean test(IntArray array) {

        int arrayLength = array.getLength();

        return arrayLength > threshold;
    }
}
