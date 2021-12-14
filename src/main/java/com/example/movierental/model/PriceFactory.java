package com.example.movierental.model;

//PriceFactory Class
//Implements the factory method
//@author Jack Murphy - 18254268

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.ServiceError;
import java.io.IOException;

import java.rmi.server.ServerCloneException;

public class PriceFactory {

    private static AbstractLogger chainLogger = RequesterClient.getChaining();

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
            chainLogger.logMessage(AbstractLogger.ERROR_INFO,"Incorrect price code entered: Must be one of 0,1,2");
            throw new ServiceException(new ServiceError(Error.INVALID_PRICE_CODE));
        }
        //return null;
    }
}
