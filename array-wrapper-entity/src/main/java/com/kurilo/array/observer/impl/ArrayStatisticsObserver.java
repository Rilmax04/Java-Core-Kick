package com.kurilo.array.observer.impl;

import com.kurilo.array.entity.ArrayStatistics;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.entity.Warehouse;
import com.kurilo.array.observer.ArrayChangeListener;

public class ArrayStatisticsObserver implements ArrayChangeListener {
    @Override
    public void onArrayChanged(IntArray array) {
        ArrayStatistics stats = calculate(array);
        Warehouse.getInstance().put(array.getId(), stats);
    }

    private ArrayStatistics calculate(IntArray arr) {
        int[] data = arr.toArray();
        if (data.length == 0) return new ArrayStatistics(0, 0, 0, 0, 0.0);

        int min = data[0], max = data[0], sum = 0;
        for (int v : data) {
            if (v < min) min = v;
            if (v > max) max = v;
            sum += v;
        }
        return new ArrayStatistics(min, max, sum, data.length, (double) sum / data.length);
    }
}