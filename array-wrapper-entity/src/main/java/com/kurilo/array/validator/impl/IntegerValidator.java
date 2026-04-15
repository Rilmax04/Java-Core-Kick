package com.kurilo.array.validator.impl;

import com.kurilo.array.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class IntegerValidator implements DataValidator {

    private static final Logger logger = LogManager.getLogger(IntegerValidator.class);

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d+$");

    @Override
    public boolean isValid(String token) {
        if (token == null) {
            logger.debug("Invalid: null token");
            return false;
        }

        String trimmed = token.strip();

        if (trimmed.isEmpty()) {
            logger.debug("Invalid: blank token");
            return false;
        }

        boolean matches = INTEGER_PATTERN.matcher(trimmed).matches();

        if (matches) {
            try {
                Integer.parseInt(trimmed);
                return true;
            } catch (NumberFormatException e) {
                logger.debug("Invalid token '{}': value out of int range", token);
                return false;
            }
        }
        logger.debug("Invalid token '{}'", token);
        return false;
    }
}