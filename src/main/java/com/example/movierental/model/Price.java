package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Price Class - Pricing Structure for the Movies
 * Author - Jack Murphy - 18254268
 */
public abstract class Price {

    @JsonIgnore
    private int price; //price of the movie per day
    private int loyaltyPoints; //loyalty points per day of rental of the movie

    /**
     * Getter for price
     * @return int price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter for Loyalty Points
     * @return int loyalty points
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Setter for Loyalty Points
     * @param loyaltyPoints
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * Returns the Charge for renting a movie with no discount
     * @return double charge
     */
    public double getCharge(){
        return getPrice();
    }

    /**
     * Calculates charge based on the type of movie and the discount applied
     * @param discount
     */
    public double getCharge(int discount){
        double d = 100.00-discount;
        double h = 100.00;
        return getPrice()*(d/h);
    }

    /**
     * Calculates the loyalty points earned based on the type of movie the number of days and
     * @param numberOfDays
     * @return int loyalty points
     */
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
