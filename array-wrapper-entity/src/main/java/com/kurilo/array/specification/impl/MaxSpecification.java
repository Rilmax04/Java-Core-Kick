package com.kurilo.array.specification.impl;

import com.kurilo.array.entity.ArrayStatistics;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.entity.Warehouse;
import com.kurilo.array.specification.Comparison;
import com.kurilo.array.specification.Specification;

public class MaxSpecification implements Specification {
    private final int target;
    private final Comparison comparison;

    public MaxSpecification(int target, Comparison comparison) {
        this.target = target;
        this.comparison = comparison;
    }

    @Override
    public boolean specify(IntArray array) {
        ArrayStatistics stats = Warehouse.getInstance().get(array.getId());
        if (stats == null) return false;
        int max = stats.getMax();
        return switch (comparison) {
            case GREATER -> max > target;
            case LESS -> max < target;
            case EQUAL -> max == target;
        };
    }
}