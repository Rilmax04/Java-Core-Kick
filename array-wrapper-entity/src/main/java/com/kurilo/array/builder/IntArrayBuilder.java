package com.kurilo.array.builder;

import com.kurilo.array.builder.impl.IntArrayBuilderImpl;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;

public interface IntArrayBuilder {
    IntArrayBuilderImpl add(int value);
    void addAll(IntArray other) throws ArrayDataException;
    IntArray build();
}
