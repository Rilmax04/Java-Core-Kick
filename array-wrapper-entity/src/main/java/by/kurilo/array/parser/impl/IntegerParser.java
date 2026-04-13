package by.kurilo.array.parser.impl;

import by.kurilo.array.parser.NumberParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class IntegerParser implements NumberParser<Integer> {
    private static final Logger LOGGER = LogManager.getLogger(IntegerParser.class);

    @Override
    public Optional<Integer> parse(String token) {
        if (token == null) {
            LOGGER.debug("Null token, skipping");
            return Optional.empty();
        }
        String trimmed = token.trim();
        if (trimmed.isEmpty()) {
            LOGGER.debug("Empty or blank token, skipping");
            return Optional.empty();
        }
        try {
            int value = Integer.parseInt(trimmed);
            return Optional.of(value);
        } catch (NumberFormatException e) {
            LOGGER.debug("Failed to parse token '{}' as integer", token);
            return Optional.empty();
        }
    }
}
