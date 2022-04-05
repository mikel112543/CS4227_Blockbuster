package com.example.movierental.abstractFactory;

/**
 * Returns the objects specific to the Movies available in Ireland family
 */
public class moviesAvailableInIrelandFactory extends AbstractMovieRegionFactory {
    /**
     * @return Irish Movies
     */
    @Override
    public moviesAvailable createMovie() {
        return new Ireland_Movies();
    }

    /**
     * @return €
     */
    @Override
    public currencySymbol createCurrencySymbol() {
        return new Ireland_Prices();
    }
}
