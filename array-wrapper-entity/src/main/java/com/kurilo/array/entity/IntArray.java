package com.kurilo.array.entity;

import com.kurilo.array.exception.ArrayDataException;
import com.kurilo.array.observer.ArrayChangeListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class IntArray {
    private static final Logger logger = LogManager.getLogger(IntArray.class);
    private static int idCounter = 0;
    private final int id;
    private int[] array;
    private final List<ArrayChangeListener> listeners = new ArrayList<>();

    public IntArray(int... values) {
        this.id = ++idCounter;
        this.array = Arrays.copyOf(values, values.length);
        logger.debug("IntArray created with id={}, length={}", id, array.length);
    }

    public int getId() { return id; }
    public int length() { return array.length; }

    public int get(int index) throws ArrayDataException {
        if (index < 0 || index >= array.length) {
            logger.error("Index {} out of bounds for length {}", index, array.length);
            throw new ArrayDataException("Index " + index + " out of bounds for length " + array.length);
        }
        logger.debug("Retrieving value at index {}: {}", index, array[index]);
        return array[index];
    }

    public void set(int index, int value) throws ArrayDataException {
        if (index < 0 || index >= array.length) {
            logger.error("Index {} out of bounds for length {}", index, array.length);
            throw new ArrayDataException("Index " + index + " out of bounds for length " + array.length);
        }
        logger.debug("Setting index {} to value {}", index, value);
        array[index] = value;
        notifyListeners(); // 🔔 Уведомляем наблюдателей об изменении
    }

    public int[] toArray() {
        logger.debug("Creating defensive copy of array");
        return Arrays.copyOf(array, array.length);
    }

    public void addListener(ArrayChangeListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public void removeListener(ArrayChangeListener listener) {
        listeners.remove(listener);
    }

    public void clearListeners() {
        listeners.clear();
    }

    private void notifyListeners() {
        for (ArrayChangeListener listener : listeners) {
            listener.onArrayChanged(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArray that = (IntArray) o;
        return id == that.id && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntArray.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("array=" + Arrays.toString(array))
                .toString();
    }
}