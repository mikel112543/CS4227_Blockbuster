package com.example.movierental.abstractFactory;

public abstract class AbstractMovieRegionFactory {
    /**
     * @return Movies available in users location
     */
    public abstract moviesAvailable createMovie();

    /**
     * @return Currency symbol in users location
     */
    public abstract currencySymbol createCurrencySymbol();

}

class Ireland_Movies implements moviesAvailable {

    /**
     * @return list of movies available in Ireland
     */
    @Override
    public String getMovieLists() {
        return "Movies.csv";
    }
}

class American_Movies implements moviesAvailable {

    /**
     * @return list of movies available in US.
     */
    @Override
    public String getMovieLists() {
        return "US_Movies.csv";
    }
}

class British_Movies implements moviesAvailable {

    /**
     * @return list of movies available in Britain
     */
    @Override
    public String getMovieLists() {
        return "British_Movies.csv";
    }
}

class Ireland_Prices implements currencySymbol {

    /**
     * @return Symbol in Euro - €
     */
    @Override
    public String getSymbol() {
        return "€";
    }
}

class American_Prices implements currencySymbol {

    /**
     * @return Symbol in Dollars - $
     */
    @Override
    public String getSymbol() {
        return "$";
    }
}

class Britain_Prices implements currencySymbol {

    /**
     * @return Symbol in Pounds - £
     */
    @Override
    public String getSymbol() {
        return "£";
    }
}

