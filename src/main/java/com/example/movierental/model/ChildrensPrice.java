package com.example.movierental.model;

//ChildrensReleasePrice Class
//
//@author Jack Murphy - 18254268

import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.states.StateHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ChildrensPrice extends Price {

    private static final double DEFAULT_PRICE = 5.0;
    //movie cost 5 per day
    //customer earns 1 loyalty points per rental per day of a movie
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public ChildrensPrice(UserRepoServiceImpl userRepoService) {
        if (authentication == null) {
            setPrice(5);
            setLoyaltyPoints(5);
        } else {
            calculateDiscount(userRepoService);
        }
    }

    @Override
    public void calculateDiscount(UserRepoServiceImpl userRepoService) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User user = userRepoService.findByUserName(authentication.getName());
            StateHandler stateHandler = new StateHandler(user);
            boolean discount = user.isDiscount();
            if (!discount) {
                setPrice(5);
                setLoyaltyPoints(5);
            } else {
                double x = stateHandler.getCurrentTier().getDiscount();
                double result = 5 * x;
                result = Math.round(result * 100.0) / 100.0;
                setPrice(result);
                setLoyaltyPoints(5);
            }
        }
    }

    @Override
    public double getDefaultPrice() {
        return DEFAULT_PRICE;
    }

    @Override
    public String toString() {
        return "ChildrensPrice{" +
                "price=" + getPriceStr() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }
}
