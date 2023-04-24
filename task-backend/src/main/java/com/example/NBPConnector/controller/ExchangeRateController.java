package com.example.NBPConnector.controller;

import com.example.NBPConnector.exception.InvalidCurrencyCodeException;
import com.example.NBPConnector.exception.TopCountExceededException;
import com.example.NBPConnector.responsebuilder.ResponseBuilders;
import com.example.NBPConnector.service.ExchangeRateService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Objects;

/**
 * ExchangeRateController is a Spring REST controller handling requests related to currency exchange rates.
 * It communicates with the NBP (National Bank of Poland) API to retrieve the requested data.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ExchangeRateController {

    private static final int MAX_TOP_COUNT = 255;

    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private ResponseBuilders responseBuilders;

    /**
     * Returns the exchange rate for the specified currency code and date.
     *
     * @param code The currency code to retrieve the exchange rate for.
     * @param date The date for which the exchange rate is requested.
     * @return A ResponseEntity containing the exchange rate data or an error message.
     */
    @GetMapping("/a/{code}/{date}")
    public ResponseEntity<String> getExchangeRateByDate(@PathVariable String code, @PathVariable String date) {
        ResponseEntity<String> apiResponse = callNbpApi(String.format("/exchangerates/rates/a/%s/%s", code, date));
        if (apiResponse.getStatusCode() == HttpStatus.OK) {
            JsonObject jsonResponse = JsonParser.parseString(Objects.requireNonNull(apiResponse.getBody())).getAsJsonObject();
            return responseBuilders.buildExchangeRateByDateResponse(jsonResponse, date);
        } else if (apiResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new InvalidCurrencyCodeException("No data / Invalid currency code: " + code);
        } else {
            return apiResponse;
        }
    }

    /**
     * Returns the maximum and minimum exchange rates for the specified currency code and the number of recent days.
     *
     * @param code The currency code to retrieve the exchange rates for.
     * @param topCount The number of recent days to consider for calculating the maximum and minimum exchange rates.
     * @return A ResponseEntity containing the maximum and minimum exchange rate data or an error message.
     */
    @GetMapping("/a/{code}/last/{topCount}")
    public ResponseEntity<String> getMaxAndMinExchangeRates(@PathVariable String code, @PathVariable int topCount) {
        if (topCount > MAX_TOP_COUNT) {
            throw new TopCountExceededException("Top count cannot be greater than " + MAX_TOP_COUNT + ".\n");
        }

        ResponseEntity<String> apiResponse = callNbpApi(String.format("/exchangerates/rates/a/%s/last/%d", code, topCount));
        if (apiResponse.getStatusCode() == HttpStatus.OK) {
            JsonObject jsonResponse = JsonParser.parseString(Objects.requireNonNull(apiResponse.getBody())).getAsJsonObject();
            return responseBuilders.buildMaxAndMinExchangeRatesResponse(jsonResponse, topCount);
        } else if (apiResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new InvalidCurrencyCodeException("No data / Invalid currency code: " + code);
        } else {
            return apiResponse;
        }
    }

    /**
     * Returns the major difference between bid and ask exchange rates for the specified currency code and the number of recent days.
     *
     * @param code The currency code to retrieve the bid and ask exchange rates for.
     * @param topCount The number of recent days to consider for calculating the major difference between bid and ask exchange rates.
     * @return A ResponseEntity containing the major difference between bid and ask exchange rates or an error message.
     */
    @GetMapping("/c/{code}/last/{topCount}")
    public ResponseEntity<String> getMajorDifferenceBetweenBidAndAsk(@PathVariable String code, @PathVariable int topCount) {
        if (topCount > MAX_TOP_COUNT) {
            throw new TopCountExceededException("Top count cannot be greater than " + MAX_TOP_COUNT + ".\n");
        }

        ResponseEntity<String> apiResponse = callNbpApi(String.format("/exchangerates/rates/c/%s/last/%d", code, topCount));
        if (apiResponse.getStatusCode() == HttpStatus.OK) {
            JsonObject jsonResponse = JsonParser.parseString(Objects.requireNonNull(apiResponse.getBody())).getAsJsonObject();
            return responseBuilders.buildMajorDifferenceBetweenBidAndAskResponse(jsonResponse, topCount);
        } else if (apiResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new InvalidCurrencyCodeException("No data / Invalid currency code: " + code);
        } else {
            return apiResponse;
        }
    }

    /**
     * Calls the NBP API with the specified path and returns the response.
     *
     * @param path The path to call in the NBP API.
     * @return A ResponseEntity containing the response data or an error message.
     */
    private ResponseEntity<String> callNbpApi(String path) {
        return exchangeRateService.callNbpApi(path);
    }
}