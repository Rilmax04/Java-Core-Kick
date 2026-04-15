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
        if (array == null || array.length() <= 1) {
            LOGGER.debug("Bubble sort: array is null or too small, nothing to sort");
            return;
        }

        int n = array.length();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }

            // Если обменов не было — массив уже отсортирован
            if (!swapped) {
                LOGGER.debug("Bubble sort: early exit at iteration {}", i);
                break;
            }
        }
        LOGGER.debug("Bubble sort completed on array of size {}", n);
    }

    @Override
    public void insertionSort(IntArray array) throws ArrayDataException {
        if (array == null || array.length() <= 1) {
            LOGGER.debug("Insertion sort: array is null or too small, nothing to sort");
            return;
        }

        int n = array.length();

        // Начинаем со второго элемента (индекс 1)
        for (int i = 1; i < n; i++) {
            int key = array.get(i);
            int j = i - 1;

            // Сдвигаем элементы, которые больше key, вправо
            while (j >= 0 && array.get(j) > key) {
                array.set(j + 1, array.get(j));
                j--;
            }

            // Вставляем key на правильную позицию
            array.set(j + 1, key);
        }
        LOGGER.debug("Insertion sort completed on array of size {}", n);
    }

    /** Вспомогательный метод для обмена элементов */
    private void swap(IntArray array, int i, int j) throws ArrayDataException {
        if (i == j) return; // Оптимизация: не менять элемент сам с собой

        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}