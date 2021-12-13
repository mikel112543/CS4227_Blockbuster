package com.example.movierental.states;

import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;

import javax.rmi.CORBA.Tie;
import java.time.LocalDate;

// Concrete State
public class Tier1 implements Tier {

    @Override
    public int getDays() {
        return 3;
    }
}