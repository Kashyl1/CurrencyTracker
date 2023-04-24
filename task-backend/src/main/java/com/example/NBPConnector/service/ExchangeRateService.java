package com.example.NBPConnector.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * ExchangeRateService is responsible for fetching data from the NBP API
 * by making HTTP requests.
 */
@Service
public class ExchangeRateService {
    private static final String NBP_API_BASE_URL = "http://api.nbp.pl/api";

    /**
     * Calls the NBP API with the given path and returns the response.
     *
     * @param path The path for the NBP API request.
     * @return ResponseEntity<String> containing the response received from the NBP API.
     */
    public ResponseEntity<String> callNbpApi(String path) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(NBP_API_BASE_URL + path, String.class);
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }
}