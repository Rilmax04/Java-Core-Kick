package com.kurilo.array.specification;

import com.kurilo.array.entity.ArrayStatistics;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.entity.Warehouse;

public class SumSpecification implements Specification {
    private final int target;
    private final Comparison comparison;

    public SumSpecification(int target, Comparison comparison) {
        this.target = target;
        this.comparison = comparison;
    }

    @Override
    public boolean specify(IntArray array) {
        ArrayStatistics stats = Warehouse.getInstance().get(array.getId());
        if (stats == null) return false;
        int sum = stats.getSum();
        return switch (comparison) {
            case GREATER -> sum > target;
            case LESS -> sum < target;
            case EQUAL -> sum == target;
        };
    }
}