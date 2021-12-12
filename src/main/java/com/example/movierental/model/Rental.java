package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// POJO
public class Rental {

    @JsonProperty("Movie")
    private final Movie movie;

    @JsonIgnore
    private final LocalDate rentLength;

    @JsonProperty("Days Remaining")
    private String daysRemaining;

    public Rental(){

    }

    public Rental(Movie movie, LocalDate rentLength) {
        this.movie = movie;
        this.rentLength = rentLength;
        calculateRemainingDays();
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDate getRentLength() {
        return rentLength;
    }

    public String calculateRemainingDays() {
        LocalDate today = LocalDate.now();
        daysRemaining = ChronoUnit.DAYS.between(today, rentLength) + " Days";
        return daysRemaining;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "movie=" + movie +
                ", rentLength=" + rentLength +
                '}';
    }

}