package com.example.movierental.model;

import com.example.movierental.service.UserRepoServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Price Class - Pricing Structure for the Movies
 * Author - Jack Murphy - 18254268
 */
public abstract class Price {

    @JsonIgnore
    private double price; //price of the movie per day
    @JsonIgnore
    private int loyaltyPoints; //loyalty points per day of rental of the movie

    /**
     * Getter for price
     * @return int price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price
     */
    public void setPrice(double price) {
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
     * Calculates charge based on the type of movie and the users tier applied
     * Tier 1 - No Discount
     * Tier 2 - 10% Discount
     * Tier 3 - 20% Discount
     * @param tier
     */
    public double getCharge(int tier){
        if(tier == 1){
            return getPrice();
        }else if(tier == 2){
            double d = 0.9;
            return getPrice()*(d);
        }else if(tier >= 3){
            double d = 0.8;
            return getPrice()*(d);
        }else{
            return getPrice();
        }
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
