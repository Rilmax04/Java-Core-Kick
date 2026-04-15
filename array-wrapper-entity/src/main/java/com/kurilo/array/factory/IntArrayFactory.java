package com.kurilo.array.factory;

import com.kurilo.array.entity.IntArray;

import java.util.List;

public interface IntArrayFactory {

     IntArray createFromList(List<Integer> numbers);
}
