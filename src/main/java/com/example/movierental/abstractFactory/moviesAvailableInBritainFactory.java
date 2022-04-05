package com.example.movierental.abstractFactory;

/**
 * Returns the objects specific to the Movies available in Britain family
 */
public class moviesAvailableInBritainFactory extends AbstractMovieRegionFactory{

    /**
     * @return British Movies
     */
    @Override
    public moviesAvailable createMovie() {
        return new British_Movies();
    }

    /**
     * @return £
     */
    @Override
    public currencySymbol createCurrencySymbol() {
        return new Britain_Prices();
    }

}
