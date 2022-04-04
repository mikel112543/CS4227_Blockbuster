package com.example.movierental.service;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;


public class CurrencyServiceImpl implements CurrencyService {
    CurrencyConverter converter;

    public CurrencyServiceImpl(){

        final String CURRENCY_CONVERTER_API_API_KEY = "83941bcb5a8bceb8ba8d";
        final String OPEN_EXCHANGE_RATES = "3747c0ac02e2431ebb6d72dfa3b88ff1";

        //Get API Key from CurrencyLayer.Com
        final String CURRENCY_LAYER = "c1dcd939f3f95761da99d9e97c7a90d0";
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
     * @return Converts Euro prices to US Dollar Price.
     */
    public double EuroToUsd() {
        return this.converter.rate("EUR", "USD");
    }


    /**
     * @return Converts Euro prices to British Pound Price.
     */
    public double EuroToBritishPound() {
        return this.converter.rate("EUR", "GBP");
    }
}
