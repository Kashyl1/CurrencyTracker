package com.example.NBPConnector.currency;

/**
 * CurrencyRate is a class representing the average exchange rate for a specific currency.
 */
public class CurrencyRate {
    private String currencyName;
    private String currencyCode;
    private double averageExchangeRate;

    /**
     * Constructs a new CurrencyRate with the specified currency name, currency code, and average exchange rate.
     *
     * @param currencyName The name of the currency.
     * @param currencyCode The code of the currency.
     * @param averageExchangeRate The average exchange rate for the currency.
     */
    public CurrencyRate(String currencyName, String currencyCode, double averageExchangeRate) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.averageExchangeRate = averageExchangeRate;
    }


    /**
     * Converts the CurrencyRate to an HTML string for display.
     *
     * @return An HTML-formatted string representing the CurrencyRate.
     */
    public String toHtmlString() {
        return String.format("<tr><td>%s</td><td>1 %s</td><td>%,.4f</td></tr>", currencyName, currencyCode, averageExchangeRate);
    }
}
