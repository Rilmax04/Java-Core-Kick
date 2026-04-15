package com.kurilo.array.parser.impl;

import com.kurilo.array.exception.ArrayDataException;
import com.kurilo.array.parser.NumberParser;
import com.kurilo.array.validator.DataValidator;
import com.kurilo.array.validator.impl.IntegerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class IntegerParser implements NumberParser{

    private static final Logger logger = LogManager.getLogger();
    private static final String LINE_DELIMITER_REGEX = "[,;\\s\\-]+";

    private final DataValidator validator = new IntegerValidator() ;

    @Override
    public int[] parseToArray(String line) throws ArrayDataException {
        if (line == null || line.isBlank()) {
            logger.debug("Input line is null or blank");
            throw new ArrayDataException("Input string cannot be null or blank");
        }

        String trimmedLine = line.strip();
        String[] tokens = trimmedLine.split(LINE_DELIMITER_REGEX);
        List<Integer> validNumbers = new ArrayList<>();

        for (String token : tokens) {
            if (token.isBlank()) {
                continue;
            }
            if (validator.isValid(token)) {
                    validNumbers.add(Integer.parseInt(token.strip()));
            } else {
                logger.warn("Skipping invalid token: '{}'", token);
            }
        }

        return validNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
