package com.example.movierental.model;

public class childrensReleasePrice extends Price{

    //movie cost 5 per day
    //customer earns 1 loyalty points per rental per day of a movie
    public childrensReleasePrice() {
        setPrice(5);
        setLoyaltyPoints(1);
    }

    @Override
    public String toString() {
        return "childrensReleasePrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }
}
