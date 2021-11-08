package com.example.movierental.model;

public class newReleasePrice extends Price{

    public newReleasePrice() {
        setPrice(10);
        setLoyaltyPoints(3);
    }

    @Override
    public String toString() {
        return "newReleasePrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }

}
