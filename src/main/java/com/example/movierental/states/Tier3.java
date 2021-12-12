package com.example.movierental.states;

import com.example.movierental.model.Movie;

import java.time.LocalDate;

// state
public class Tier3 extends Rental {

    public Tier3(Movie movie, LocalDate rentLength) {
        super(movie, rentLength.plusDays(14));
    }

}