package com.example.movierental.model;

public class standardReleasePrice extends Price{

    public standardReleasePrice() {
        setPrice(8);
        setLoyaltyPoints(2);
    }

    @Override
    public String toString() {
        return "standardReleasePrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }
}
