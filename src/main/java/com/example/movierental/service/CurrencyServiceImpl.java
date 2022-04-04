package com.example.movierental.service;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;


public class CurrencyServiceImpl implements CurrencyService {
    CurrencyConverter converter;

    public CurrencyServiceImpl(){
        //Get API Key from CurrencyLayer.Com
        final String CURRENCY_LAYER = "c1dcd939f3f95761da99d9e97c7a90d0";
        // Init converter with your API key
        this.converter = new CurrencyConverter(
                new ConfigBuilder()
                        .currencyLayerApiKey(CURRENCY_LAYER)
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
