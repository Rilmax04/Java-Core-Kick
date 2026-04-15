package com.kurilo.array;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;
import com.kurilo.array.factory.impl.IntArrayFactoryImpl;
import com.kurilo.array.parser.NumberParser;
import com.kurilo.array.parser.impl.IntegerParser;
import com.kurilo.array.reader.DataReader;
import com.kurilo.array.reader.impl.DataReaderImpl;
import com.kurilo.array.service.ArraySortingService;
import com.kurilo.array.service.ArrayStatisticsService;
import com.kurilo.array.service.impl.ArraySortingServiceImpl;
import com.kurilo.array.service.impl.ArrayStatisticsServiceImpl;
import com.kurilo.array.validator.DataValidator;
import com.kurilo.array.validator.impl.IntegerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "data/numbers.txt";

        DataReader dataReader = new DataReaderImpl();
        NumberParser parser = new IntegerParser();
        DataValidator validator = new IntegerValidator();
        IntArrayFactoryImpl factory = new IntArrayFactoryImpl();
        ArrayStatisticsService statisticsService = new ArrayStatisticsServiceImpl();
        ArraySortingService sortingService = new ArraySortingServiceImpl();

        try {
            List<String> lines = dataReader.readAllLinesFromFile(filePath);
            LOGGER.info("Read {} lines from file", lines.size());

            List<Integer> validNumbers = new ArrayList<>();

            for (String line : lines) {
                if (line == null || line.isBlank()) {
                    LOGGER.debug("Skipping empty line");
                    continue;
                }
                String[] tokens = line.split("[;,\\s\\-]+");
                for (String token : tokens) {
                    if (validator.isValid(token)) {
         //               Optional<Integer> number = parser.parse(token);
           //             number.ifPresent(validNumbers::add);
                    } else {
                        LOGGER.debug("Invalid token: '{}'", token);
                    }
                }
            }

            LOGGER.info("Found {} valid numbers", validNumbers.size());

            if (validNumbers.isEmpty()) {
                LOGGER.warn("No valid numbers found. Exiting.");
                return;
            }

            IntArray array = factory.createFromList(validNumbers);
            LOGGER.info("Created IntArray: {}", array);

            statisticsService.findMin(array).ifPresent(min -> LOGGER.info("Min: {}", min));
            statisticsService.findMax(array).ifPresent(max -> LOGGER.info("Max: {}", max));
            statisticsService.sum(array).ifPresent(sum -> LOGGER.info("Sum: {}", sum));
            statisticsService.average(array).ifPresent(avg -> LOGGER.info("Average: {}", avg));

            IntArray arrayForBubbleSort = factory.createFromList(validNumbers);
            sortingService.bubbleSort(arrayForBubbleSort);
            LOGGER.info("Bubble sort result: {}", arrayForBubbleSort);

            IntArray arrayForQuickSort = factory.createFromList(validNumbers);
            sortingService.insertionSort(arrayForQuickSort);
            LOGGER.info("Quick sort result: {}", arrayForQuickSort);

        } catch (ArrayDataException e) {
            LOGGER.error("Failed to read file: {}", filePath, e);
        }
    }
}