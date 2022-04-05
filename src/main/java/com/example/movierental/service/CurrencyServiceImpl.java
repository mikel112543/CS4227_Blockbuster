package com.example.movierental.service;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;


/**
 *
 * Author - Bandile Tshabalala
 */
public class CurrencyServiceImpl implements CurrencyService {
    CurrencyConverter converter;

    /**
     * Connects to an API Currency Converter online and seeks,
     * the current exchange rate values in  real time. Some Currency converters
     * are functional at times.
     * Author - Bandile Tshabalala
     */
    public CurrencyServiceImpl(){
        //Get API Key from CurrencyLayer.Com
        final String CURRENCY_LAYER = "c1dcd939f3f95761da99d9e97c7a90d0";
        //Get API KET from CurrencyConverterApi.Com
        final String CURRENCY_CONVERTER_API_API_KEY = "83941bcb5a8bceb8ba8d";
        //Get API Key from OpenExchangeRates.Com
        final String OPEN_EXCHANGE_RATES = "3747c0ac02e2431ebb6d72dfa3b88ff1";

        // Init converter with your API key
        this.converter = new CurrencyConverter(
                new ConfigBuilder()
                        .openExchangeRatesApiKey(OPEN_EXCHANGE_RATES)
                        //.currencyConverterApiApiKey(CURRENCY_CONVERTER_API_API_KEY)
                        //.currencyLayerApiKey(CURRENCY_LAYER)
                        .build()
        );
    }

    /**
     * Method that converts Euro to United States Dollars
     * @return currency rate from Euros to US Dollars.
     */
    public double EuroToUsd() {
        return this.converter.rate("EUR", "USD");
    }


    /**
     * Method that converts Euro to United States Dollars
     * @return currency rate from Euros to British Pounds.
     */
    public double EuroToBritishPound() {
        return this.converter.rate("EUR", "GBP");
    }
}
