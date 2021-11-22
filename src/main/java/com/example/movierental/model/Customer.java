package com.example.movierental.model;

import java.util.ArrayList;
import java.util.List;

//Customer Class
//
//@author Jack Murphy - 18254268

public class Customer extends User{

    private int loyaltyPoints;
    private int tier;
    private List<Rental> customerMovies;

    public Customer(){
        //empty constructor
    }

    public Customer(int userID, int loyaltyPoints){
        super(userID);
        this.loyaltyPoints = loyaltyPoints;
        //empty constructor
    }

    //constructor for new Customer
    public Customer(int userID, String username, String password, Boolean banned) {
        super(userID, username, password, banned);
    }

    //Constructor for existing Customer
    public Customer(int userID, String username, String password, Boolean banned, int loyaltyPoints, int tier, List<Rental> customerMovies) {
        super(userID, username, password, banned);
        this.loyaltyPoints = loyaltyPoints;
        this.tier = tier;
        this.customerMovies = customerMovies;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public List<Rental> getCustomerMovies() {
        return getCustomerMovies();
    }

    public void setUserMovies(List<Rental> customerMovies) {
        this.customerMovies = customerMovies;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" +
                "Name: " + getUsername() +
                "Loyalty Points attained=" + loyaltyPoints +
                ", Current Tier=" + tier +
                ", Rented Movies=" + customerMovies +
                '}';
    }
}