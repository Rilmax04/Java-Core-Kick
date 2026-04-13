package by.kurilo.array.builder;

import by.kurilo.array.entity.IntArray;

import java.util.ArrayList;
import java.util.List;

public class IntArrayBuilder {
    private List<Integer> values = new ArrayList<>();

    public IntArrayBuilder add(int value) {
        values.add(value);
        return this;
    }

    public IntArrayBuilder addAll(IntArray other) {
        for (int i = 0; i < other.length(); i++) {
            values.add(other.get(i));
        }
        return this;
    }

    public IntArray build() {
        int[] arr = values.stream().mapToInt(i -> i).toArray();
        return new IntArray(arr);
    }
}