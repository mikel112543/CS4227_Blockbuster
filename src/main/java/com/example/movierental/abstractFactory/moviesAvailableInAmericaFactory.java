package com.example.movierental.abstractFactory;

/**
 *  Returns the objects specific to the Movies available in America family
 */
public class moviesAvailableInAmericaFactory extends AbstractMovieRegionFactory {
    /**
     * @return American Movies
     */
    @Override
    public moviesAvailable createMovie() {
        return new American_Movies();
    }

    /**
     * @return $
     */
    @Override
    public currencySymbol createCurrencySymbol() {
        return new American_Prices();
    }
}
