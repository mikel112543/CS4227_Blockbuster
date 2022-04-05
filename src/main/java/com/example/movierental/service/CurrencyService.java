package com.example.movierental.service;

public interface CurrencyService {

    /**
     * @return the conversion rate from Euros to US Dollars.
     */
    double EuroToUsd();

    /**
     * @return the conversion rate from Euros to British Pounds.
     */
    double EuroToBritishPound();
}
