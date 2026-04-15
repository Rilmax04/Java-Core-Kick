package com.kurilo.array.specification.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.specification.Comparison;
import com.kurilo.array.specification.Specification;

public class LengthSpecification implements Specification {
    private final int target;
    private final Comparison comparison;

    public LengthSpecification(int target, Comparison comparison) {
        this.target = target;
        this.comparison = comparison;
    }

    @Override
    public boolean specify(IntArray array) {
        int length = array.length();
        return switch (comparison) {
            case GREATER -> length > target;
            case LESS -> length < target;
            case EQUAL -> length == target;
        };
    }
}