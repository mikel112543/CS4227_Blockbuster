package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Rental {

    @JsonProperty("Movie")
    protected final Movie movie;

    @JsonIgnore
    protected final LocalDateTime rentLength;

    @JsonProperty("Days Remaining")
    private int daysRemaining;


    public Rental(Movie movie, LocalDateTime rentLength) {
        this.movie = movie;
        this.rentLength = rentLength;
        calculateRemainingDays();
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getRentLength() {
        return rentLength;
    }

    public int calculateRemainingDays() {
        LocalDate today = LocalDate.now();
        daysRemaining = (int) (ChronoUnit.DAYS.between(today, rentLength));
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