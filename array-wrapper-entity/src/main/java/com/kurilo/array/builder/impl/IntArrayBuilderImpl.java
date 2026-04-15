package com.kurilo.array.builder.impl;

import com.kurilo.array.builder.IntArrayBuilder;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;

import java.util.ArrayList;
import java.util.List;

public class IntArrayBuilderImpl implements IntArrayBuilder {
    private List<Integer> values = new ArrayList<>();

    @Override
    public IntArrayBuilderImpl add(int value) {
        values.add(value);
        return this;
    }
    @Override
    public void addAll(IntArray other) throws ArrayDataException {
        for (int i = 0; i < other.length(); i++) {
            values.add(other.get(i));
        }
    }
    @Override
    public IntArray build() {
        int[] arr = values.stream().mapToInt(i -> i).toArray();
        return new IntArray(arr);
    }
}