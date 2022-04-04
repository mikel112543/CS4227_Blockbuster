package com.example.movierental.abstractFactory;

public abstract class AbstractMovieRegionFactory {
    public abstract moviesAvailable createMovie();
    public abstract regionPrices createPrice();
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
        return "UK_Movies.csv";
    }
}

//------------
interface regionPrices {
    String getPrice();

}

class Ireland_Prices implements regionPrices {

    @Override
    public String getPrice() {
        return "€";
    }
}

class American_Prices implements regionPrices {

    @Override
    public String getPrice() {
        return "$";
    }
}

class Britain_Prices implements regionPrices {

    @Override
    public String getPrice() {
        return "$";
    }
}

