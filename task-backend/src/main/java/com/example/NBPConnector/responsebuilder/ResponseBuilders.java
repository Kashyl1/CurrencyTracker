package com.example.NBPConnector.responsebuilder;

import com.example.NBPConnector.currency.CurrencyRate;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * ResponseBuilders class is responsible for building HTML table responses
 * for exchange rate queries, based on provided JSON data received from NBP API.
 */
@Component
public class ResponseBuilders {


    /**
     * Builds an HTML table response displaying the average exchange rate of the currency
     * for the specified date.
     *
     * @param jsonResponse The JSON data received from NBP API.
     * @param date The date for which the average exchange rate is calculated.
     * @return ResponseEntity<String> containing the HTML table response with the average exchange rate.
     */
    public ResponseEntity<String> buildExchangeRateByDateResponse(JsonObject jsonResponse, String date) {
        try {
            String currencyName = jsonResponse.get("currency").getAsString();
            String currencyCode = jsonResponse.get("code").getAsString();
            double averageExchangeRate = jsonResponse.getAsJsonArray("rates").get(0).getAsJsonObject().get("mid").getAsDouble();
            CurrencyRate currencyRate = new CurrencyRate(currencyName, currencyCode, averageExchangeRate);
            StringBuilder frontEnd = new StringBuilder();
            frontEnd.append("<table border=\"5\">");
            frontEnd.append(String.format("<h2>Average Exchange rate from %s:</h2>", date));
            frontEnd.append("<tr><th>Currency name</th><th>Code</th><th>Average exchange rate</th></tr>");
            frontEnd.append(currencyRate.toHtmlString());
            frontEnd.append("</table>");
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(frontEnd.toString());
        } catch (JsonSyntaxException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing NBP API response: " + e.getMessage() + "\n");
        }
    }

    /**
     * Builds an HTML table response displaying the maximum and minimum average exchange rates
     * for the currency in the last specified number of quotations.
     *
     * @param jsonResponse The JSON data received from NBP API.
     * @param topCount The number of last quotations to be considered.
     * @return ResponseEntity<String> containing the HTML table response with max and min exchange rates.
     */
    public ResponseEntity<String> buildMaxAndMinExchangeRatesResponse(JsonObject jsonResponse, int topCount) {
        try {
            String currencyName = jsonResponse.get("currency").getAsString();
            String currencyCode = jsonResponse.get("code").getAsString();

            JsonArray ratesArray = jsonResponse.getAsJsonArray("rates");
            double maxRate = Double.MIN_VALUE;
            double minRate = Double.MAX_VALUE;
            String maxRateDate = "";
            String minRateDate = "";

            for (JsonElement rateElement : ratesArray) {
                JsonObject rate = rateElement.getAsJsonObject();
                double averageExchangeRate = rate.get("mid").getAsDouble();
                String currentDate = rate.get("effectiveDate").getAsString();

                if (averageExchangeRate > maxRate) {
                    maxRate = averageExchangeRate;
                    maxRateDate = currentDate;
                }

                if (averageExchangeRate < minRate) {
                    minRate = averageExchangeRate;
                    minRateDate = currentDate;
                }
            }

            StringBuilder frontEnd = new StringBuilder();
            frontEnd.append(String.format("<h2>Max and Min Exchange Rates for %s (%s) in last %d quotations:</h2>", currencyName, currencyCode, topCount));
            frontEnd.append("<table border=\"5\">");
            frontEnd.append("<tr><th>Type</th><th>Average exchange rate</th><th>Date</th></tr>");
            frontEnd.append(String.format("<tr><td>Max</td><td>%,.4f</td><td>%s</td></tr>", maxRate, maxRateDate));
            frontEnd.append(String.format("<tr><td>Min</td><td>%,.4f</td><td>%s</td></tr>", minRate, minRateDate));
            frontEnd.append("</table>");

            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(frontEnd.toString());
        } catch (JsonSyntaxException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing NBP API response: " + e.getMessage() + "\n");
        }
    }

    /**
     * Builds an HTML table response displaying the major difference between bid and ask values
     * for the currency in the last specified number of quotations.
     *
     * @param jsonResponse The JSON data received from NBP API.
     * @param topCount The number of last quotations to be considered.
     * @return ResponseEntity<String> containing the HTML table response with bid, ask and their difference.
     */
    public ResponseEntity<String> buildMajorDifferenceBetweenBidAndAskResponse(JsonObject jsonResponse, int topCount) {
        try {
            String currencyName = jsonResponse.get("currency").getAsString();
            String currencyCode = jsonResponse.get("code").getAsString();

            JsonArray ratesArray = jsonResponse.getAsJsonArray("rates");
            double maxDifference = 0;
            String maxDifferenceDate = "";
            double bidValue = 0;
            double askValue = 0;

            for (JsonElement rateElement : ratesArray) {
                JsonObject rate = rateElement.getAsJsonObject();
                double bid = rate.get("bid").getAsDouble();
                double ask = rate.get("ask").getAsDouble();
                double difference = Math.abs(ask - bid);
                String currentDate = rate.get("effectiveDate").getAsString();

                if (difference > maxDifference) {
                    maxDifference = difference;
                    maxDifferenceDate = currentDate;
                    bidValue = bid;
                    askValue = ask;
                }
            }

            StringBuilder frontEnd = new StringBuilder();
            frontEnd.append(String.format("<h2>Major difference between Bid and Ask for %s (%s) in last %d quotations:</h2>", currencyName, currencyCode, topCount));
            frontEnd.append("<table border=\"5\">");
            frontEnd.append("<tr><th>Type</th><th>Value</th><th>Date</th></tr>");
            frontEnd.append(String.format("<tr><td>Bid</td><td>%,.4f</td><td>%s</td></tr>", bidValue, maxDifferenceDate));
            frontEnd.append(String.format("<tr><td>Ask</td><td>%,.4f</td><td>%s</td></tr>", askValue, maxDifferenceDate));
            frontEnd.append(String.format("<tr><td>Difference</td><td>%,.4f</td><td>%s</td></tr>", maxDifference, maxDifferenceDate));
            frontEnd.append("</table>");
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(frontEnd.toString());
        } catch (JsonSyntaxException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing NBP API response: " + e.getMessage() + "\n");
        }
    }
}
