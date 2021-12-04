package com.example.movierental.model;

//Price Class
//
//@author Jack Murphy - 18254268

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Price {

    @JsonIgnore
    private int price; //price of the movie per day
    private int loyaltyPoints; //loyalty points per day of rental of the movie

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    //Calculates charge based on number of days and type of movie
    public int getCharge(int numberOfDays){
        return getPrice()*numberOfDays;
    }

    //Calculates loyalty point earned based on number of days and type of movie
    public int getLoyaltyPointsEarned(int numberOfDays){
        return getLoyaltyPoints()*numberOfDays;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}
