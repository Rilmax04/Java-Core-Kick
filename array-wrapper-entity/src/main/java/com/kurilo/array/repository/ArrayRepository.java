package com.kurilo.array.repository;

import com.kurilo.array.entity.IntArray;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ArrayRepository {
    void addArray(IntArray array);

    void removeArray(IntArray array);

    List<IntArray> queryArrays(Predicate<IntArray> predicate);

    void sortArrays (Comparator<IntArray> comparator);
}
