package by.kurilo.array.validator.impl;

import by.kurilo.array.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class IntegerValidator implements DataValidator {
    private static final Logger LOGGER = LogManager.getLogger(IntegerValidator.class);

    @Override
    public boolean isValid(String token) {
        if (token == null || token.isBlank()) {
            LOGGER.debug("Invalid: null or blank token");
            return false;
        }
        String trimmed = token.trim();
        if (trimmed.isEmpty()) return false;

        // Allow optional leading minus
        if (trimmed.charAt(0) == '-') {
            if (trimmed.length() == 1) return false;
            trimmed = trimmed.substring(1);
        }
        // Check that all remaining characters are digits
        for (char c : trimmed.toCharArray()) {
            if (!Character.isDigit(c)) {
                LOGGER.debug("Invalid token: {} contains non-digit", token);
                return false;
            }
        }
        // Additionally try to parse to catch overflow (optional)
        try {
            Integer.parseInt(token.trim());
            return true;
        } catch (NumberFormatException e) {
            LOGGER.debug("Invalid token: {} causes NumberFormatException", token);
            return false;
        }
    }
}
