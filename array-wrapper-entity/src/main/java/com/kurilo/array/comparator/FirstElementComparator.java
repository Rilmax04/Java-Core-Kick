package com.kurilo.array.comparator;

import com.kurilo.array.entity.IntArray;

import java.util.Comparator;

public class FirstElementComparator implements Comparator<IntArray> {
    @Override
    public int compare(IntArray firstArray, IntArray secondArray) {

        int firstArrayValue = firstArray.getElement(1);
        int secondArrayValue = secondArray.getElement(1);

        return Integer.compare(firstArrayValue, secondArrayValue);
    }
}
