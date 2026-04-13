package by.kurilo.array.reader.impl;


import by.kurilo.array.exception.ArrayDataException;
import by.kurilo.array.reader.ReadFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileReader implements ReadFile {
    private static final Logger LOGGER = LogManager.getLogger(TextFileReader.class);

    @Override
    public List<String> readAllLines(String filePath) throws ArrayDataException {
        LOGGER.info("Reading file: {}", filePath);
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            LOGGER.info("Successfully read {} lines", lines.size());
            return lines;
        } catch (IOException e) {
            LOGGER.error("Failed to read file: {}", filePath, e);
            throw new ArrayDataException("Cannot read file: " + filePath, e);
        }
    }
}