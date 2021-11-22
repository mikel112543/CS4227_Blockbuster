package com.example.movierental.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// POJO
public class Rental {

    private final Movie movie;
    private final LocalDate rentLength;

    public Rental(Movie movie, LocalDate rentLength) {
        this.movie = movie;
        this.rentLength = rentLength;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDate getRentLength() {
        return rentLength;
    }

    public long calculateRemainingDays() {
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(rentLength, today);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "movie=" + movie +
                ", rentLength=" + rentLength +
                '}';
    }

}