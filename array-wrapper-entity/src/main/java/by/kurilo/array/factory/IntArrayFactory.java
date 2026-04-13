package by.kurilo.array.factory;

import by.kurilo.array.builder.IntArrayBuilder;
import by.kurilo.array.entity.IntArray;

import java.util.List;

public class IntArrayFactory {

    public IntArray createFromList(List<Integer> numbers) {
        IntArrayBuilder builder = new IntArrayBuilder();
        for (Integer number : numbers) {
            builder.add(number);
        }
        return builder.build();
    }
}
