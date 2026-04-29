package com.kurilo.array.observer.impl;

import com.kurilo.array.entity.ArrayStatistics;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.observer.ArrayEvent;
import com.kurilo.array.observer.ArrayObserver;
import com.kurilo.array.service.impl.ArrayStatisticsServiceImpl;
import com.kurilo.array.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStatisticsObserver implements ArrayObserver {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public void updateChanged(ArrayEvent event) {

        IntArray source = event.getSource();
        long id = source.getId();

        logger.info("Observer triggered for array id: {}. Recalculating statistics...", id);

        ArrayStatisticsServiceImpl statistic = new ArrayStatisticsServiceImpl();

        int min = statistic.findMin(source).orElse(0);
        int max = statistic.findMax(source).orElse(0);
        int sum = statistic.sum(source).orElse(0);
        double average = statistic.average(source).orElse(0);

        ArrayStatistics stats = new ArrayStatistics(min,max,sum,average);

        ArrayWarehouse warehouse=ArrayWarehouse.getInstance();
        warehouse.put(id, stats);
    }

}