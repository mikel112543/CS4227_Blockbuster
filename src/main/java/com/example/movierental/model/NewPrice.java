package com.example.movierental.model;

//NewReleasePrice Class
//
//@author Jack Murphy - 18254268

import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.states.StateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class NewPrice extends Price{
    //movie cost 10 per day
    //customer earns 3 loyalty points per rental per day of a movie
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    private static final double DEFAULT_PRICE = 10.0;

    public NewPrice(UserRepoServiceImpl userRepoService) {
        if (authentication == null) {
            setPrice(10);
            setLoyaltyPoints(3);
        } else {
            User user = userRepoService.findByUserName(authentication.getName());
            StateHandler stateHandler = new StateHandler(user);
            boolean discount = user.isDiscount();
            if (discount == false) {
                setPrice(10);
                setLoyaltyPoints(3);
            } else {
                double x = stateHandler.getCurrentTier().getDiscount();
                double result = 10 * x;
                result = Math.round(result * 100.0) / 100.0;
                setPrice(result);
                setLoyaltyPoints(3);
            }
        }
    }

    @Override
    public double getDefaultPrice() {
        return DEFAULT_PRICE;
    }

    @Override
    public String toString() {
        return "NewPrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }

}
