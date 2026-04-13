package by.kurilo.array.service.impl;

import by.kurilo.array.entity.IntArray;
import by.kurilo.array.service.ArraySortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArraySortingServiceImpl implements ArraySortingService {
    private static final Logger LOGGER = LogManager.getLogger(ArraySortingServiceImpl.class);

    @Override
    public void bubbleSort(IntArray array) {
        if (array.length() <= 1) {
            LOGGER.debug("Bubble sort: array too small, nothing to sort");
            return;
        }
        int n = array.length();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        LOGGER.debug("Bubble sort completed on array of size {}", n);
    }

    @Override
    public void quickSort(IntArray array) {
        if (array.length() <= 1) {
            LOGGER.debug("Quick sort: array too small, nothing to sort");
            return;
        }
        quickSortRecursive(array, 0, array.length() - 1);
        LOGGER.debug("Quick sort completed on array of size {}", array.length());
    }

    private void quickSortRecursive(IntArray array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortRecursive(array, low, pivotIndex - 1);
            quickSortRecursive(array, pivotIndex + 1, high);
        }
    }

    private int partition(IntArray array, int low, int high) {
        int pivot = array.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array.get(j) <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(IntArray array, int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}