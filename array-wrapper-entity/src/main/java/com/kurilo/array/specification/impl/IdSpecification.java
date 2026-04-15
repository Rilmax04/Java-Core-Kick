package com.kurilo.array.specification.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.specification.Specification;

public class IdSpecification implements Specification {
    private final int id;

    public IdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(IntArray array) {
        return array.getId() == id;
    }
}