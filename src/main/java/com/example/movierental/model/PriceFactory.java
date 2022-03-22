package com.example.movierental.model;

//PriceFactory Class
//Implements the factory method
//@author Jack Murphy - 18254268

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.Dispatcher;

import com.example.movierental.logger.LoggerInterceptor;
import com.example.movierental.service.UserRepoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PriceFactory {

    private static final Dispatcher dispatcher = new Dispatcher();
    private static final Logger log = LoggerFactory.getLogger(PriceFactory.class);

    //getPrice returns the required Price object based on the priceCode
    //PRICE CODES: 0 = NewPrice, 1 = StandardPrice, 2 = ChildrensPrice
    public Price getPrice(int priceCode, UserRepoServiceImpl userRepoService){

        if(priceCode == 0){
            return new NewPrice(userRepoService);
        } else if(priceCode == 1){
            return new StandardPrice(userRepoService);
        }else if(priceCode == 2){
            return new ChildrensPrice(userRepoService);
        }else{
            dispatcher.logMessage(log,"Incorrect price code entered: Must be one of 0,1,2", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.INVALID_PRICE_CODE));
        }
    }
}
