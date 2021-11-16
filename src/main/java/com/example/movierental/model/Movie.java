package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.Duration;
import java.time.LocalTime;

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

    //PRICE CODES: 0 = newReleasePrice, 1 = standardReleasePrice, 2 = childrensReleasePrice
    //parameterised constructor
    public Movie(String title, String genre, String description, Duration length, int priceCode, int movieId, int movieRating) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.length = length;
        this.movieId = movieId;
        this.movieRating = movieRating;
        if(priceCode == 0){
            this.price = new newReleasePrice();
        } else if(priceCode == 1){
            this.price = new standardReleasePrice();
        }else if(priceCode == 2){
            this.price = new childrensReleasePrice();
        }else{
            System.out.println("Enter error checking here");
        }
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getLength() {
        return length;
    }

    public void setLength(Duration length) {
        this.length = length;
    }

    public Price getPrice(){
        return price;
    }

    //public int getPrice() {
    //    return price.getPrice();
    //}

    //not sure if it is necessary to be able to manually update values for each movie
    //PRICE CODES: 0 = newReleasePrice, 1 = standardReleasePrice, 2 = childrensReleasePrice
    public void setPrice(int priceCode) {
        if(priceCode == 0){
            this.price = new newReleasePrice();
        } else if(priceCode == 1){
            this.price = new standardReleasePrice();
        }else if(priceCode == 2){
            this.price = new childrensReleasePrice();
        }else{
            System.out.println("Enter error checking here");
        }
    }

    public int getMovieId() {
        return movieId;
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
