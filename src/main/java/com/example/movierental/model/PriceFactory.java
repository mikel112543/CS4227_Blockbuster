package com.example.movierental.model;

//PriceFactory Class
//Implements the factory method
//@author Jack Murphy - 18254268

public class PriceFactory {

    //getPrice returns the required Price object based on the priceCode
    //PRICE CODES: 0 = newReleasePrice, 1 = standardReleasePrice, 2 = childrensReleasePrice
    public Price getPrice(int priceCode){

        if(priceCode == 0){
            return new newReleasePrice();
        } else if(priceCode == 1){
            return new standardReleasePrice();
        }else if(priceCode == 2){
            return new childrensReleasePrice();
        }else{
            System.out.println("Enter error checking here");
        }
        return null;
    }
}
