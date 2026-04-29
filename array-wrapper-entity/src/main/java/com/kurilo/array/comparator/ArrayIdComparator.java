package com.kurilo.array.comparator;

import com.kurilo.array.entity.IntArray;

import java.util.Comparator;

public class ArrayIdComparator implements Comparator<IntArray> {

    @Override
    public int compare (IntArray firstArray,IntArray secondArray){
        return Long.compare(firstArray.getId(), secondArray.getId());
    }
}
