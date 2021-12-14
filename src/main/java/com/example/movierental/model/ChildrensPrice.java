package com.example.movierental.model;

//ChildrensReleasePrice Class
//
//@author Jack Murphy - 18254268

import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.states.StateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ChildrensPrice extends Price {

    //movie cost 5 per day
    //customer earns 1 loyalty points per rental per day of a movie
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public ChildrensPrice(UserRepoServiceImpl userRepoService) {
        if (authentication == null) {
            setPrice(5);
            setLoyaltyPoints(1);
        } else {
            User user = userRepoService.findByUserName(authentication.getName());
            StateHandler stateHandler = new StateHandler(user);
            boolean discount = user.isDiscount();
            if (discount == false) {
                setPrice(5);
                setLoyaltyPoints(1);
            } else {
                double x = stateHandler.getCurrentTier().getDiscount();
                double result = 5 * x;
                result = Math.round(result * 100.0) / 100.0;
                setPrice(result);
                setLoyaltyPoints(1);
            }
        }

    }

    @Override
    public String toString() {
        return "ChildrensPrice{" +
                "price=" + getPrice() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }
}
