package com.example.movierental.states;

import com.example.movierental.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Super state
public abstract class Rental {

    @JsonProperty("Movie")
    protected final Movie movie;

    @JsonIgnore
    protected final LocalDate rentLength;

    @JsonProperty("Days Remaining")
    protected String daysRemaining;

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