package com.example.movierental.model;

//NewReleasePrice Class
//
//@author Jack Murphy - 18254268

public class NewPrice extends Price{

    //movie cost 10 per day
    //customer earns 3 loyalty points per rental per day of a movie
    public NewPrice() {
        setPrice(10);
        setLoyaltyPoints(3);
    }

    @Override
    public String toString() {
        return "NewPrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }

}
