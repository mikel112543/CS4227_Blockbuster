package com.example.movierental.abstractFactory;

public class moviesAvailableInBritainFactory extends AbstractMovieRegionFactory{

    @Override
    public moviesAvailable createMovie() {
        return new British_Movies();
    }

    @Override
    public currencySymbol createCurrencySymbol() {
        return new Britain_Prices();
    }

}
