package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {

    @JsonIgnore
    private int userID;

    @JsonProperty("Username")
    private String username;

    @JsonIgnore
    private String password;

    @JsonProperty("Banned")
    private boolean banned;

    @JsonProperty("Loyalty Points")
    private int loyaltyPoints;

    @JsonProperty("Tier")
    private int tier;

    @JsonIgnore
    private List<Rental> rentedMovies;

    @JsonProperty("Is Admin")
    private boolean isAdmin;

    public User() {
    }

    public User(int userID, String username, String password, boolean banned, int loyaltyPoints, int tier, List<Rental> rentedMovies, boolean isAdmin) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.banned = banned;
        this.loyaltyPoints = loyaltyPoints;
        this.tier = tier;
        this.rentedMovies = rentedMovies;
        this.isAdmin = isAdmin;
    }

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
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

    public List<Rental> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(List<Rental> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                '}';
    }
}