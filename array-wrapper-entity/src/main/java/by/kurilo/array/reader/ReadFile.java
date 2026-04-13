package by.kurilo.array.reader;

import by.kurilo.array.exception.ArrayDataException;

import java.util.List;

public interface ReadFile {

    List<String> readAllLines(String filePath) throws ArrayDataException;
}
