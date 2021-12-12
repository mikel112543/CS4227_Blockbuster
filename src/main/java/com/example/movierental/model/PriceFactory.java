package com.example.movierental.model;

//PriceFactory Class
//Implements the factory method
//@author Jack Murphy - 18254268

public class PriceFactory {

    //getPrice returns the required Price object based on the priceCode
    //PRICE CODES: 0 = NewPrice, 1 = StandardPrice, 2 = ChildrensPrice
    public Price getPrice(int priceCode){

        if(priceCode == 0){
            return new NewPrice();
        } else if(priceCode == 1){
            return new StandardPrice();
        }else if(priceCode == 2){
            return new ChildrensPrice();
        }else{
            System.out.println("Enter error checking here");
        }
        return null;
    }
}
