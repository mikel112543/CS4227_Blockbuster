package com.example.movierental.model;

import com.example.movierental.service.CurrencyService;
import com.example.movierental.service.CurrencyServiceImpl;

import java.text.DecimalFormat;

interface Currency {
    double convertCurrency();
    String convertSymbol();
    /**
     * @return symbol and price of the movie,
     * depending on the origin of the user.
     */
    String getPrice();
}

class IrelandCurrency  implements Currency{
    String symbol = "€";
    double price;

    public IrelandCurrency(double price) {
        this.price = price;
    }

    @Override
    public double convertCurrency() {
        return this.price;
    }

    @Override
    public String convertSymbol() {
        return this.symbol;
    }

    @Override
    public String getPrice() {
        return convertSymbol()+this.convertCurrency();
    }
}

class AmericanCurrency  implements Currency {
    CurrencyService service = new CurrencyServiceImpl();
    String symbol = "$";
    double price;

    public AmericanCurrency(double price) {
        this.price = price;
    }

    @Override
    public double convertCurrency() {
        DecimalFormat df = new DecimalFormat("#.##");
        double price = this.price * service.EuroToUsd();
        return Double.parseDouble(df.format(price));
    }

    @Override
    public String convertSymbol() {
        return this.symbol;
    }

    @Override
    public String getPrice() {
        return convertSymbol() + this.convertCurrency();
    }
}

class BritainCurrency  implements Currency {
    CurrencyService service = new CurrencyServiceImpl();
    String symbol = "£";
    double price;

    public BritainCurrency(double price) {
        this.price = price;
    }

    @Override
    public double convertCurrency() {
        DecimalFormat df = new DecimalFormat("#.##");
        double price = this.price * service.EuroToBritishPound();
        return Double.parseDouble(df.format(price));
    }

    @Override
    public String convertSymbol() {
        return this.symbol;
    }

    @Override
    public String getPrice() {
        return convertSymbol() + this.convertCurrency();
    }
}

interface CurrencyAdapter {
    String getPrice();
}

class AmericanCurrencyAdapter implements CurrencyAdapter {
    Currency americanCurrency;

    public AmericanCurrencyAdapter(Currency americanCurrency) {
        this.americanCurrency = americanCurrency;
    }

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

    @Override
    public String getPrice() {
        return this.britainCurrency.getPrice();
    }
}

