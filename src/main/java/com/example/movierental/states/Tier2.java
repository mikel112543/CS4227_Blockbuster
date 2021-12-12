package com.example.movierental.states;

import com.example.movierental.model.Movie;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// state
public class Tier2 extends Rental {

    public Tier2(Movie movie, LocalDate rentLength) {
        super(movie, rentLength.plusDays(7));
    }

}