package com.example.movierental.abstractFactory;

//--------------
public class moviesAvailableInIrelandFactory extends AbstractMovieRegionFactory {
    @Override
    public moviesAvailable createMovie() {
        return new Ireland_Movies();
    }

    @Override
    public regionPrices createPrice() {
        return new Ireland_Prices();
    }
}
