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

import java.util.HashMap;
import java.util.Map;


public class PriceFactory {

    private static final int NEW_PRICE = 0;
    private static final int STANDARD_PRICE = 1;
    private static final int CHILDREN_PRICE = 2;

    private static final Dispatcher dispatcher = new Dispatcher();
    private static final Logger log = LoggerFactory.getLogger(PriceFactory.class);
    private static final Map<Integer, Price> prices = new HashMap<>();

    //Flyweight Pattern
    public static Price getPrice(int priceCode, UserRepoServiceImpl userRepoService) {
        Price price;
        if (prices.containsKey(priceCode)) {
            prices.get(priceCode).calculateDiscount(userRepoService);
            return prices.get(priceCode);
        }
        switch (priceCode) {
            case (NEW_PRICE):
                price = new NewPrice(userRepoService);
                break;
            case (STANDARD_PRICE):
                price = new StandardPrice(userRepoService);
                break;
            case (CHILDREN_PRICE):
                price = new ChildrensPrice(userRepoService);
                break;
            default:
                dispatcher.logMessage(log, "Incorrect price code entered: Must be one of 0,1,2", LoggerInterceptor.ERROR);
                throw new ServiceException(new ServiceError(Error.INVALID_PRICE_CODE));
        }
        prices.put(priceCode, price);
        return price;
    }
}
