package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.Duration;

//Movie Class
//
//@author Jack Murphy - 18254268

@JsonRootName("Movie Details")
public class Movie {

    @JsonIgnore
    private int movieId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Description")
    private String description;

    //changed to duration
    @JsonIgnore
    private double length;

    @JsonProperty("Movie Length")
    private String movieLength;

    @JsonProperty("Rating")
    private int movieRating;

    @JsonIgnore
    private Price price;

    @JsonProperty("Price")
    private String priceStr;

    public Movie() {
        //empty constructor
    }

    //PRICE CODES: 0 = newReleasePrice, 1 = standardReleasePrice, 2 = childrensReleasePrice
    //parameterised constructor
    public Movie(int movieId, String title, String genre, String description, double length, int priceCode, int movieRating) {

        PriceFactory p = new PriceFactory();
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.length = length;
        this.price = p.getPrice(priceCode);
        this.movieRating = movieRating;
        getPriceStr();
        getMovieLength();
    }


    public int getMovieId() {
        return movieId;
    }

    //calls prices get charge method
    public int getCharge(int numberOfDays){
        return price.getCharge(numberOfDays);
    }

    //calls prices get loyalt points earned method
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

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Price getPrice(){
        return price;
    }

    //not sure if it is necessary to be able to manually update values for each movie
    //PRICE CODES: 0 = newReleasePrice, 1 = standardReleasePrice, 2 = childrensReleasePrice
    public void setPrice(int priceCode) {
        PriceFactory p = new PriceFactory();
        this.price = p.getPrice(priceCode);
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieLength() {
        return Double.toString(length) + "hrs";
    }

    public String getPriceStr() {
        priceStr = "€"+ price.getPrice();
        return priceStr;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", length='" + length + '\'' +
                ", movieId=" + movieId +
                ", movieRating=" + movieRating +
                ", price=" + price +
                '}';
    }
}
