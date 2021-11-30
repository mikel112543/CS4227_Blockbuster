package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.Duration;

//Movie Class
//
//@author Jack Murphy - 18254268

@JsonRootName("Movie Details")
public class Movie {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Description")
    private String description;

    //changed to duration
    @JsonProperty("Length")
    private Duration length;

    @JsonProperty("Movie ID")
    private int movieId;

    @JsonProperty("Rating")
    private int movieRating;

    @JsonProperty("Price")
    private Price price;

    public Movie() {
        //empty constructor
    }

    public Movie(String title, String genre, String description, Duration length, int priceCode, int movieId, int movieRating) {
        PriceFactory p = new PriceFactory();
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.length = length;
        this.movieId = movieId;
        this.movieRating = movieRating;
        this.price = p.getPrice(priceCode);
    }

    public int getCharge(int numberOfDays){
        return price.getCharge(numberOfDays);
    }

    public int getLoyaltyPointsEarned(int numberOfDays){
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

    public Duration getLength() {
        return length;
    }

    public Price getPrice(){
        return price;
    }

    public void setPrice(int priceCode) {
        PriceFactory p = new PriceFactory();
        this.price = p.getPrice(priceCode);
    }

    public int getMovieId() {
        return movieId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", length=" + length +
                ", movieId=" + movieId +
                ", movieRating=" + movieRating +
                ", price=" + price.toString() +
                '}';
    }
}
