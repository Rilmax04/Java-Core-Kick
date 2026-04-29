package com.kurilo.array.service.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;
import com.kurilo.array.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArraySortingServiceImpl implements ArraySortingService {

    private static final Logger LOGGER = LogManager.getLogger(ArraySortingServiceImpl.class);

    @Override
    public void bubbleSort(IntArray array) throws ArrayDataException {
        if (array == null || array.getLength() <= 1) {
            LOGGER.debug("Bubble sort: array is null or too small, nothing to sort");
            return;
        }

        int n = array.getLength();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (array.getElement(j) > array.getElement(j + 1)) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                LOGGER.debug("Bubble sort: early exit at iteration {}", i);
                break;
            }
        }
        LOGGER.debug("Bubble sort completed on array of size {}", n);
    }

    @Override
    public void insertionSort(IntArray array)  {
        if (array == null || array.getLength() <= 1) {
            LOGGER.debug("Insertion sort: array is null or too small, nothing to sort");
            return;
        }
        int n = array.getLength();
        for (int i = 1; i < n; i++) {
            int key = array.getElement(i);
            int j = i - 1;

            while (j >= 0 && array.getElement(j) > key) {
                array.setElement(j + 1, array.getElement(j));
                j--;
            }
            array.setElement(j + 1, key);
        }
        LOGGER.debug("Insertion sort completed on array of size {}", n);
    }

    private void swap(IntArray array, int i, int j) {
        if (i == j) return;

        int temp = array.getElement(i);
        array.setElement(i, array.getElement(j));
        array.setElement(j, temp);
    }
}