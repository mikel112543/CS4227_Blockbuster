package com.example.movierental.states;

//Concrete State
public class Tier3 implements Tier {
    @Override
    public int getDays() {
        return 10;
    }

    @Override
    public double getDiscount() {
        return 0.85;
    }
}