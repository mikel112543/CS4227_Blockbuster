package com.example.movierental.model;

import com.example.movierental.service.UserRepoServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.HashMap;
import java.util.Map;

//Movie Class
//
//@author Jack Murphy - 18254268

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
    @JsonIgnore
    private int movieId;
    @JsonIgnore
    private String movieCoverUrl;

    //optional properties
    @JsonIgnore
    private Price price;
    @JsonProperty("Price")
    private String priceStr;

    public Movie () {}

    public int getMovieId() {
        return movieId;
    }

    /**
     * Calculates the charge based on the type of movie (type of price object)
     * Calls getCharge in Price Class
     * @return double charge for renting movie
     */
    public double getCharge(){
        return price.getCharge();
    }


    public String getMovieCoverUrl(){
        return movieCoverUrl;
    }


    /**
     * Calls the Converter API and converts the price depending on user location.
     * @return Price and currency symbol
     */
    public String getPriceStr() {
        priceStr = price.getPriceStr();
        return priceStr;
    }

    public String getChargeStr() {
        priceStr = "€"+ price.getCharge();
        return priceStr;
    }

    public int getLoyaltyPoints(){
        return price.getLoyaltyPoints();
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

    public Price getPriceObj() {
        return price;
    }


    private Movie(MovieBuilder builder) {
        this.title = builder.title;
        this.genre = builder.genre;
        this.description = builder.description;
        this.length = builder.length;
        this.movieId = builder.movieId;
        this.movieCoverUrl = builder.movieCoverUrl;
        this.price = builder.price;
    }

    public static class MovieBuilder {
        @JsonProperty("Title")
        private String title;
        @JsonProperty("Genre")
        private String genre;
        @JsonProperty("Description")
        private String description;
        @JsonProperty("Length")
        private String length;
        @JsonProperty("MovieID")
        private int movieId;
        @JsonIgnore
        private String movieCoverUrl;

        //optional parameters
        @JsonIgnore
        private Price price;

        public MovieBuilder(String title, String genre, String description, String length, int movieId, String movieCoverUrl) { //required parameters in here only
            this.title = title;
            this.genre = genre;
            this.description = description;
            this.length = length;
            this.movieId = movieId;
            this.movieCoverUrl = movieCoverUrl;
        }


        @JsonIgnore
        public MovieBuilder setPrice(int priceCode, UserRepoServiceImpl userRepoService) {
            this.price = PriceFactory.getPrice(priceCode, userRepoService);
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", length='" + length + '\'' +
                ", movieId=" + movieId +
                ", price=" + price +
                '}';
    }
}
