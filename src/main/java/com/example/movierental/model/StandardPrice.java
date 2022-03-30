package com.example.movierental.model;

//StandardReleasePrice Class
//
//@author Jack Murphy - 18254268

import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.states.StateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class StandardPrice extends Price {

    static final double DEFAULT_PRICE = 8.0;
    //movie cost 8 per day
    //customer earns 2 loyalty points per rental per day of a movie
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public StandardPrice(UserRepoServiceImpl userRepoService) {
        if (authentication == null) {
            setPrice(8);
            setLoyaltyPoints(2);
        } else {
            calculateDiscount(userRepoService);
        }
           /* User user = userRepoService.findByUserName(authentication.getName());
            StateHandler stateHandler = new StateHandler(user);
            boolean discount = user.isDiscount();
            if (discount == false) {
                setPrice(8);
                setLoyaltyPoints(2);
            } else {
                double x = stateHandler.getCurrentTier().getDiscount();
                double result = 8 * x;
                result = Math.round(result * 100.0) / 100.0;
                setPrice(result);
                setLoyaltyPoints(2);
            }
        }*/
    }
    @Override
    public void calculateDiscount(UserRepoServiceImpl userRepoService) {
        if(authentication != null) {
            User user = userRepoService.findByUserName(authentication.getName());
            StateHandler stateHandler = new StateHandler(user);
            boolean discount = user.isDiscount();
            if (!discount) {
                setPrice(8);
                setLoyaltyPoints(2);
            } else {
                double x = stateHandler.getCurrentTier().getDiscount();
                double result = 8 * x;
                result = Math.round(result * 100.0) / 100.0;
                setPrice(result);
                setLoyaltyPoints(2);
            }
        }
    }

    @Override
    public double getDefaultPrice() {
        return DEFAULT_PRICE;
    }

    @Override
    public String toString() {
        return "StandardPrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }
}
