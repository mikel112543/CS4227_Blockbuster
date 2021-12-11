package com.example.movierental.states;

import com.example.movierental.model.Movie;

import java.time.LocalDate;

// State
public class Tier1 extends Rental {

    public Tier1(Movie movie, LocalDate rentLength) {
        super(movie, rentLength.plusDays(3));
    }
}