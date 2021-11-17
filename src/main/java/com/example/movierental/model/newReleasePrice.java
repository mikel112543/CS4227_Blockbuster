package com.example.movierental.model;

//NewReleasePrice Class
//
//@author Jack Murphy - 18254268

public class newReleasePrice extends Price{

    //movie cost 10 per day
    //customer earns 3 loyalty points per rental per day of a movie
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
