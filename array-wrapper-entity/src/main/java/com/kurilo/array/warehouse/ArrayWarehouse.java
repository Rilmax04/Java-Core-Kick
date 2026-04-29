package com.kurilo.array.warehouse;

import com.kurilo.array.entity.ArrayStatistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ArrayWarehouse {
    public static final Logger logger = LogManager.getLogger();
    private static ArrayWarehouse instance;
    private final Map<Long, ArrayStatistics> statisticsMap = new HashMap<>();

    private ArrayWarehouse() {}

    public static ArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayWarehouse();
        }
        return instance;
    }

    public void put(long arrayId, ArrayStatistics statistics) {
        statisticsMap.put(arrayId, statistics);
        logger.info("Statistics saved/updated in Warehouse for array id: {}", arrayId);
    }

    public void remove(long arrayId) {
        logger.info("Remove statistics from Warehouse for array id: {}", arrayId);
        statisticsMap.remove(arrayId);
    }
}