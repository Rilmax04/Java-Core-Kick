package com.kurilo.array.comparator;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.service.impl.ArrayStatisticsServiceImpl;
import java.util.Comparator;

public class ArraySumComparator implements Comparator<IntArray> {
    @Override
    public int compare (IntArray firstArray , IntArray secondArray){

        ArrayStatisticsServiceImpl statisticsService = new ArrayStatisticsServiceImpl();

        int firstSum = statisticsService.sum(firstArray).orElse(0);
        int secondSum = statisticsService.sum(secondArray).orElse(0);

        return Integer.compare(firstSum, secondSum);
    }
}
