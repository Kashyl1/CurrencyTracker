package com.example.NBPConnector.exception;

/**
 * TopCountExceededException is a custom exception class for handling cases
 * when the topCount value exceeds the allowed limit.
 */
public class TopCountExceededException extends RuntimeException {
    /**
     * Constructs a new TopCountExceededException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TopCountExceededException(String message) {
        super(message);
    }
}