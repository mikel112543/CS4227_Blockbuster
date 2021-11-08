package com.example.movierental.model;

public class childrensReleasePrice extends Price{

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
