package com.kurilo.array.reader;

import com.kurilo.array.exception.ArrayDataException;

import java.util.List;

public interface DataReader {

    List<String> readAllLinesFromFile(String filePath) throws ArrayDataException;
}
