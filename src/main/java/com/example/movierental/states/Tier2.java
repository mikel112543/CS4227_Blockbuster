package com.example.movierental.states;

// Concrete state
public class Tier2 implements Tier {
    @Override
    public int getDays() {
        return 5;
    }
}