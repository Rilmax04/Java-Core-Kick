package com.kurilo.array.entity;

import com.kurilo.array.observer.ArrayEvent;
import com.kurilo.array.observer.ArrayObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.StringJoiner;

public class IntArray {
    private static final Logger logger = LogManager.getLogger(IntArray.class);
    private static long idCounter =0;
    private final long id;
    private int[] array;
    private ArrayObserver observer;

    public IntArray(int... values) {
        this.id = ++idCounter;
        this.array = Arrays.copyOf(values, values.length);
        logger.debug("IntArray created with id={}, getLength={}", id, array.length);
    }

    public long getId() {
        return id;
    }
    public int getLength() {
        return array.length;
    }

    public int getElement(int index)  {
        if (index < 0 || index >= array.length) {
            logger.error("Index {} out of bounds for getLength {}", index, array.length);
        }
        logger.debug("Retrieving value at index {}: {}", index, array[index]);
        return array[index];
    }

    public void setElement(int index, int value) {
        if (index < 0 || index >= array.length) {
            logger.error("Index {} out of bounds for getLength {}", index, array.length);
        }
        logger.debug("Setting index {} to value {}", index, value);
        array[index] = value;
        notifyObserver();
    }

    public int[] toArray() {
        logger.debug("Creating defensive copy of array");
        return Arrays.copyOf(array, array.length);
    }

    public void attach(ArrayObserver observer){
        if (observer!=null){
            logger.debug("Observer attached to array id: {}", id);
            this.observer=observer;
        }
    }

    public void detach(){
        logger.debug("Observer detached from array id: {}", id);
        this.observer=null;
    }

    public void notifyObserver(){
        ArrayEvent event = new ArrayEvent(this);
        logger.debug("Notifying observers for array id: {}", id);
        observer.updateChanged(event);
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
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntArray.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("array=" + Arrays.toString(array))
                .toString();
    }
}