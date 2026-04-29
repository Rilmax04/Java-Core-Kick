package com.kurilo.array.repository.impl;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.repository.ArrayRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ArrayRepositoryImpl implements ArrayRepository {
    private static final Logger logger = LogManager.getLogger();
    private static ArrayRepositoryImpl instance;
    private final List<IntArray> arrays = new ArrayList<>();

    private ArrayRepositoryImpl() {}

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addArray(IntArray customArray) {
        arrays.add(customArray);
        long id = customArray.getId();
        logger.info("{} add array", id);
    }

    @Override
    public void removeArray(IntArray customArray) {
        arrays.remove(customArray);
        long id = customArray.getId();
        logger.info("{} remove array", id);
    }


    @Override
    public List<IntArray> queryArrays(Predicate<IntArray> predicate) {
        logger.debug("Query arrays");
        List<IntArray> result = arrays.stream().filter(predicate).toList();
        int size=result.size();
        logger.info("Querry executed. Found {} arrays", size);
        return result;
    }

    @Override
    public void sortArrays(Comparator<IntArray> comparator) {
        logger.info("Sort arrays");
        arrays.sort(comparator);
        logger.debug("Sorted successfully");
    }
}