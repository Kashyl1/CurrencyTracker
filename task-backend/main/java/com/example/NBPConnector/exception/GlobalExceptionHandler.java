package com.example.NBPConnector.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler is a centralized exception handler that captures
 * custom exceptions and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles TopCountExceededExceptions and returns a BAD_REQUEST HTTP response.
     *
     * @param ex The TopCountExceededException.
     * @param request The WebRequest.
     * @return ResponseEntity<Object> containing the error message and HTTP status.
     */
    @ExceptionHandler({ TopCountExceededException.class })
    public ResponseEntity<Object> handleTopCountExceededException(TopCountExceededException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidCurrencyCodeExceptions and returns a NOT_FOUND HTTP response.
     *
     * @param ex The InvalidCurrencyCodeException.
     * @param request The WebRequest.
     * @return ResponseEntity<Object> containing the error message and HTTP status.
     */
    @ExceptionHandler({ InvalidCurrencyCodeException.class })
    public ResponseEntity<Object> handleInvalidCurrencyCodeException(InvalidCurrencyCodeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
