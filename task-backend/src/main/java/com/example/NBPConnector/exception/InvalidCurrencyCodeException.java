package com.example.NBPConnector.exception;

/**
 * InvalidCurrencyCodeException is a custom exception class for handling cases
 * when the provided currency code is invalid.
 */
public class InvalidCurrencyCodeException extends RuntimeException {
    /**
     * Constructs a new InvalidCurrencyCodeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidCurrencyCodeException(String message) {
        super(message);
    }
}