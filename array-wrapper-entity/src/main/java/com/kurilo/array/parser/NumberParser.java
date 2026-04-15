package com.kurilo.array.parser;

import com.kurilo.array.exception.ArrayDataException;

public interface NumberParser {

    int[] parseToArray(String token) throws ArrayDataException;
}
