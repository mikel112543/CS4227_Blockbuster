package com.example.movierental.model;

import com.example.movierental.abstractFactory.*;
import com.example.movierental.service.CurrencyService;
import com.example.movierental.service.CurrencyServiceImpl;

import java.text.DecimalFormat;

/*
* Adapter Pattern
* @Author - Bandile Tshabalala
* */

/**
 * Original interface that returns the price in Euros
 */
interface Currency {
    double convertCurrency();
    String convertSymbol();
    /**
     * @return gets the relevant symbol and the currency rate
     * for the user
     */
    String getPrice();
}

class IrelandCurrency  implements Currency{
    double price;

    public IrelandCurrency(double price) {
        this.price = price;
    }

    /**
     * @return Price in Euros
     */
    @Override
    public double convertCurrency() {
        return this.price;
    }

    /**
     * @return Symbol in Euro - €
     */
    @Override
    public String convertSymbol() {
        AbstractMovieRegionFactory factory = new moviesAvailableInIrelandFactory();
        currencySymbol currencySymbol = factory.createCurrencySymbol();
        return currencySymbol.getSymbol();
    }

    /**
     * @return Price for users in Ireland (€7.00)
     */
    @Override
    public String getPrice() {
        return convertSymbol() + this.convertCurrency();
    }
}

class AmericanCurrency  implements Currency {
    CurrencyService service = new CurrencyServiceImpl();
    double price;

    public AmericanCurrency(double price) {
        this.price = price;
    }

    /**
     * @return The converted currency from Euros to US Dollars
     */
    @Override
    public double convertCurrency() {
        DecimalFormat df = new DecimalFormat("#.##");
        double price = this.price * service.EuroToUsd();
        return Double.parseDouble(df.format(price));
    }

    /**
     * @return The converted currency symbol to $
     */
    @Override
    public String convertSymbol() {
        AbstractMovieRegionFactory factory = new moviesAvailableInAmericaFactory();
        currencySymbol currencySymbol = factory.createCurrencySymbol();
        return currencySymbol.getSymbol();
    }

    /**
     * @return Price for users in America ($7.64)
     */
    @Override
    public String getPrice() {
        return convertSymbol() + this.convertCurrency();
    }
}

class BritainCurrency  implements Currency {
    CurrencyService service = new CurrencyServiceImpl();
    double price;

    public BritainCurrency(double price) {
        this.price = price;
    }

    /**
     * @return The converted currency from Euros to British Pounds
     */
    @Override
    public double convertCurrency() {
        DecimalFormat df = new DecimalFormat("#.##");
        double price = this.price * service.EuroToBritishPound();
        return Double.parseDouble(df.format(price));
    }

    /**
     * @return The converted currency symbol to £
     */
    @Override
    public String convertSymbol() {
        AbstractMovieRegionFactory factory = new moviesAvailableInBritainFactory();
        currencySymbol currencySymbol = factory.createCurrencySymbol();
        return currencySymbol.getSymbol();
    }

    /**
     * @return Price for users in the United Kingdom (£5.84)
     */
    @Override
    public String getPrice() {
        return convertSymbol() + this.convertCurrency();
    }
}

interface CurrencyAdapter {
    /**
     * @return The converted price
     */
    String getPrice();
}

class AmericanCurrencyAdapter implements CurrencyAdapter {
    Currency americanCurrency;

    public AmericanCurrencyAdapter(Currency americanCurrency) {
        this.americanCurrency = americanCurrency;
    }

    /**
     * @return The price in US Dollars
     */
    @Override
    public String getPrice() {
        return this.americanCurrency.getPrice();
    }
}

class BritainCurrencyAdapter implements CurrencyAdapter {
    Currency britainCurrency;

    public BritainCurrencyAdapter(Currency britainCurrency) {
        this.britainCurrency = britainCurrency;
    }

    /**
     * @return The price in British Pounds
     */
    @Override
    public String getPrice() {
        return this.britainCurrency.getPrice();
    }
}

