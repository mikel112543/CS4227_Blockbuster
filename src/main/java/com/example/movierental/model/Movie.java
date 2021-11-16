package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.LocalTime;

//Movie Class
//
//@author Jack Murphy - 18254268


public class Movie {


    private String title;


    private String genre;


    private String description;


    private LocalTime length;


    private LocalTime rentLength;


    private int price;


    private int movieId;


    private int movieRating;

    public Movie() {
        //empty constructor
    }

    //parameterised constructor
    public Movie(String title, String genre, String description, LocalTime length, LocalTime rentLength, int price, int movieId, int movieRating) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.length = length;
        this.rentLength = rentLength;
        this.price = price;
        this.movieId = movieId;
        this.movieRating = movieRating;
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

    public LocalTime getLength() {
        return length;
    }

    public void setLength(LocalTime length) {
        this.length = length;
    }

    public LocalTime getRentLength() {
        return rentLength;
    }

    public void setRentLength(LocalTime rentLength) {
        this.rentLength = rentLength;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    //***MUST ADD IN MOVIE LENGTH***
    @Override
    public String toString() {
        return new StringBuilder().append("Title: ").append(this.title).append(", Genre: ").append(this.genre).append(", Description: ").append(this.description).append(", Length: ").append(this.length).append(", Rent Length: ").append(this.rentLength).append(", Price: ").append(this.price)
                .append(", Movie ID").append(this.movieId).append(", Movie Rating: ").append(this.movieRating).toString();
    }
}