package com.kurilo.array.repository;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.entity.Warehouse;
import com.kurilo.array.observer.ArrayChangeListener;
import com.kurilo.array.observer.impl.ArrayStatisticsObserver;
import com.kurilo.array.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ArrayRepository {
    private static final Logger logger = LogManager.getLogger(ArrayRepository.class);
    private static ArrayRepository instance;
    private final List<IntArray> arrays = new ArrayList<>();
    private final ArrayChangeListener statsObserver = new ArrayStatisticsObserver();

    private ArrayRepository() {
        logger.debug("ArrayRepository initialized (Singleton)");
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            logger.debug("Creating ArrayRepository instance");
            instance = new ArrayRepository();
        }
        return instance;
    }

    public boolean add(IntArray array) {
        if (array == null) {
            logger.warn("Attempt to add null IntArray");
            return false;
        }
        if (arrays.add(array)) {
            array.addListener(statsObserver);
            statsObserver.onArrayChanged(array); // первичный расчёт
            logger.debug("Added IntArray id={} to repository", array.getId());
            return true;
        }
        return false;
    }

    public boolean remove(IntArray array) {
        if (array == null) return false;
        if (arrays.remove(array)) {
            array.removeListener(statsObserver);
            array.clearListeners();
            Warehouse.getInstance().remove(array.getId());
            logger.debug("Removed IntArray id={} from repository", array.getId());
            return true;
        }
        return false;
    }

    public boolean removeById(int id) {
        IntArray found = getById(id);
        return found != null && remove(found);
    }

    public IntArray getById(int id) {
        for (IntArray arr : arrays) {
            if (arr.getId() == id) return arr;
        }
        return null;
    }

    public List<IntArray> getAll() {
        return new ArrayList<>(arrays);
    }

    public int size() {
        return arrays.size();
    }

    // В классе ArrayRepository
    public List<IntArray> find(Specification specification) {
        List<IntArray> result = new ArrayList<>();
        for (IntArray array : arrays) {
            if (specification.specify(array)) {
                result.add(array);
            }
        }
        return result;
    }

    public List<IntArray> sort(Comparator<IntArray> comparator) {
        List<IntArray> sorted = new ArrayList<>(arrays);
        sorted.sort(comparator);
        logger.debug("Sorted {} arrays", sorted.size());
        return sorted;
    }

    public void clear() {
        for (IntArray arr : arrays) {
            arr.clearListeners();
            Warehouse.getInstance().remove(arr.getId());
        }
        arrays.clear();
        logger.debug("Repository cleared");
    }
}