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
    @JsonIgnore
    private int discount;

    /**
     * Getter for price
     * @return int price
     */
    public double getPrice() {
        String location= "American";
        if(location.equals("American")) {
            Currency american = new AmericanCurrency(price);
            CurrencyAdapter americanCurrencyAdapter = new AmericanCurrencyAdapter(american);
            price = Double.parseDouble(americanCurrencyAdapter.getPrice());
        }
        return price;
    }

    public abstract void calculateDiscount(UserRepoServiceImpl userRepoService);

    public double getDefaultPrice() {
        return 0.0;
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
