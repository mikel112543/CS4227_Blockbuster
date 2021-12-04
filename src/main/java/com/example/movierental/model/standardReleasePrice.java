package com.example.movierental.model;

//StandardReleasePrice Class
//
//@author Jack Murphy - 18254268

public class standardReleasePrice extends Price{

    //movie cost 8 per day
    //customer earns 2 loyalty points per rental per day of a movie
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
