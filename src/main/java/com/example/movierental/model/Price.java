package com.example.movierental.model;

public abstract class Price {


    private int price; //price of the movie
    private int loyaltyPoints; //loyalty points per rental of the movie

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

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}
