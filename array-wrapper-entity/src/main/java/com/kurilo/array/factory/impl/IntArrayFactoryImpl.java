package com.kurilo.array.factory.impl;

import com.kurilo.array.builder.impl.IntArrayBuilderImpl;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.factory.IntArrayFactory;

import java.util.List;

public class IntArrayFactoryImpl implements IntArrayFactory {

    @Override
    public IntArray createFromList(List<Integer> numbers) {
        IntArrayBuilderImpl builder = new IntArrayBuilderImpl();
        for (Integer number : numbers) {
            builder.add(number);
        }
        return builder.build();
    }
}
