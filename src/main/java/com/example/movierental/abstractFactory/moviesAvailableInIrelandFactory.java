package com.example.movierental.abstractFactory;

//--------------
public class moviesAvailableInIrelandFactory extends AbstractMovieRegionFactory {
    @Override
    public moviesAvailable createMovie() {
        return new Ireland_Movies();
    }

    @Override
    public currencySymbol createCurrencySymbol() {
        return new Ireland_Prices();
    }
}
