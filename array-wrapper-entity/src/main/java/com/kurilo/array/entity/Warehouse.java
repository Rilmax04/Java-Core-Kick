package com.kurilo.array.entity;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private final Map<Integer, ArrayStatistics> statisticsMap = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(int arrayId, ArrayStatistics statistics) {
        statisticsMap.put(arrayId, statistics);
    }

    public ArrayStatistics get(int arrayId) {
        return statisticsMap.get(arrayId);
    }

    public void remove(int arrayId) {
        statisticsMap.remove(arrayId);
    }
}