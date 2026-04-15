package com.kurilo.array.entity;

public class ArrayStatistics {
    private final int min;
    private final int max;
    private final int sum;
    private final int count;
    private final double average;

    public ArrayStatistics(int min, int max, int sum, int count, double average) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.count = count;
        this.average = average;
    }

    public int getMin() { return min; }
    public int getMax() { return max; }
    public int getSum() { return sum; }
    public int getCount() { return count; }
    public double getAverage() { return average; }

    @Override
    public String toString() {
        return String.format("[min=%d, max=%d, sum=%d, avg=%.2f, count=%d]", min, max, sum, average, count);
    }
}