package com.example.movierental.states;

// Concrete State
public class Tier1 implements Tier {

    @Override
    public int getDays() {
        return 3;
    }

    @Override
    public double getDiscount() {
        return 0.95;
    }
}