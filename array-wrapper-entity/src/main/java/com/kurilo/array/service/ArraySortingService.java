package com.kurilo.array.service;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;

public interface ArraySortingService {
    void bubbleSort(IntArray array) throws ArrayDataException;
    void insertionSort(IntArray array) throws ArrayDataException;
}
