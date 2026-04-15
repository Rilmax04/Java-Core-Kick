package com.kurilo.array.comparator;

import com.kurilo.array.entity.ArrayStatistics;
import com.kurilo.array.entity.IntArray;
import com.kurilo.array.entity.Warehouse;

import java.util.Comparator;

public class ArrayComparators {
    public static Comparator<IntArray> byId() {
        return Comparator.comparingInt(IntArray::getId);
    }

    public static Comparator<IntArray> byLength() {
        return Comparator.comparingInt(IntArray::length);
    }

    public static Comparator<IntArray> byFirstElement() {
        return (a, b) -> {
            try {
                return Integer.compare(a.get(0), b.get(0));
            } catch (Exception e) {
                return Integer.compare(a.length(), b.length());
            }
        };
    }

    public static Comparator<IntArray> bySum() {
        return (a, b) -> {
            ArrayStatistics sa = Warehouse.getInstance().get(a.getId());
            ArrayStatistics sb = Warehouse.getInstance().get(b.getId());
            if (sa == null || sb == null) return 0;
            return Integer.compare(sa.getSum(), sb.getSum());
        };
    }

    public static Comparator<IntArray> byAverage() {
        return (a, b) -> {
            ArrayStatistics sa = Warehouse.getInstance().get(a.getId());
            ArrayStatistics sb = Warehouse.getInstance().get(b.getId());
            if (sa == null || sb == null) return 0;
            return Double.compare(sa.getAverage(), sb.getAverage());
        };
    }

    public static Comparator<IntArray> byMax() {
        return (a, b) -> {
            ArrayStatistics sa = Warehouse.getInstance().get(a.getId());
            ArrayStatistics sb = Warehouse.getInstance().get(b.getId());
            if (sa == null || sb == null) return 0;
            return Integer.compare(sa.getMax(), sb.getMax());
        };
    }

    public static Comparator<IntArray> byMin() {
        return (a, b) -> {
            ArrayStatistics sa = Warehouse.getInstance().get(a.getId());
            ArrayStatistics sb = Warehouse.getInstance().get(b.getId());
            if (sa == null || sb == null) return 0;
            return Integer.compare(sa.getMin(), sb.getMin());
        };
    }
}