package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

//builder pattern here...

@JsonRootName("Movie Details")
public class Movie {

    //required parameters:
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("Movie ID")
    private int movieId;

    //optional properties
    @JsonProperty("Price")
    private Price price;

    public Movie () {}

    public int getLoyaltyPointsEarned(int numberOfDays) {
        return price.getLoyaltyPointsEarned(numberOfDays);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getLength() {
        return length;
    }

    public int getMovieId() {
        return movieId;
    }

    public Price getPrice() {
        return price;
    }

    private Movie(MovieBuilder builder) {
        this.title = builder.title;
        this.genre = builder.genre;
        this.description = builder.description;
        this.length = builder.length;
        this.movieId = builder.movieId;
        this.price = builder.price;
    }

    public static class MovieBuilder {
        private String title;
        private String genre;
        private String description;
        private String length;
        private int movieId;
        //optional parameters
        private Price price;

        public MovieBuilder(String title, String genre, String description, String length, int movieId) { //required parameters in here only
            this.title = title;
            this.genre = genre;
            this.description = description;
            this.length = length;
            this.movieId = movieId;
        }


        public MovieBuilder setPrice(int priceCode) {
            PriceFactory p = new PriceFactory();
            this.price = p.getPrice(priceCode);
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}



