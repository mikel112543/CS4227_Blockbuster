package com.example.movierental.model;

//ChildrensReleasePrice Class
//
//@author Jack Murphy - 18254268

public class ChildrensPrice extends Price{

    //movie cost 5 per day
    //customer earns 1 loyalty points per rental per day of a movie
    public ChildrensPrice() {
        setPrice(5);
        setLoyaltyPoints(1);
    }

    @Override
    public String toString() {
        return "ChildrensPrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }
}
