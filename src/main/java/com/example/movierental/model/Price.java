package com.example.movierental.model;

import com.example.movierental.service.UserLocationServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Price Class - Pricing Structure for the Movies
 * Author - Jack Murphy - 18254268
 */
public abstract class Price {
    UserLocationServiceImpl userLocationService = new UserLocationServiceImpl();

    @JsonIgnore
    private double price; //price of the movie per day
    @JsonIgnore
    private int loyaltyPoints; //loyalty points per day of rental of the movie
    @JsonIgnore
    private int discount;

    protected Price() throws IOException, GeoIp2Exception {
    }

    /**
     * Getter for price
     * @return int price
     */
    public double getPrice() {
        return price;
    }

    public String getPriceStr(){
        String userLocation = userLocationService.getLocation();;
        switch (userLocation) {
            case "United States":
                Currency american = new AmericanCurrency(price);
                CurrencyAdapter americanAdapter = new AmericanCurrencyAdapter(american);
                return americanAdapter.getPrice();
            case "United Kingdom":
                Currency Britain = new BritainCurrency(price);
                CurrencyAdapter britainAdapter = new BritainCurrencyAdapter(Britain);
                return britainAdapter.getPrice();
            case "Ireland":
                Currency Ireland = new IrelandCurrency(price);
                CurrencyAdapter irelandAdapter = new BritainCurrencyAdapter(Ireland);
                return irelandAdapter.getPrice();
        }
        return null;
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
