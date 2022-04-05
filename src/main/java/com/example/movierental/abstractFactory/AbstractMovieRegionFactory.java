package com.example.movierental.abstractFactory;

public abstract class AbstractMovieRegionFactory {
    public abstract moviesAvailable createMovie();
    public abstract currencySymbol createCurrencySymbol();

}

class Ireland_Movies implements moviesAvailable {

    @Override
    public String getMovieLists() {
        return "Movies.csv";
    }
}

class American_Movies implements moviesAvailable {

    @Override
    public String getMovieLists() {
        return "US_Movies.csv";
    }
}

class British_Movies implements moviesAvailable {

    @Override
    public String getMovieLists() {
        return "British_Movies.csv";
    }
}

class Ireland_Prices implements currencySymbol {

    @Override
    public String getSymbol() {
        return "€";
    }
}

class American_Prices implements currencySymbol {

    @Override
    public String getSymbol() {
        return "$";
    }
}

class Britain_Prices implements currencySymbol {

    @Override
    public String getSymbol() {
        return "£";
    }
}

