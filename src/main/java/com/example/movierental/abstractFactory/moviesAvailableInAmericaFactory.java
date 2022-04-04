package com.example.movierental.abstractFactory;

public class moviesAvailableInAmericaFactory extends AbstractMovieRegionFactory {
    @Override
    public moviesAvailable createMovie() {
        return new American_Movies();
    }

    @Override
    public regionPrices createPrice() {
        return new American_Prices();
    }
}
