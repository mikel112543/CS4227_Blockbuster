package com.example.movierental.model;

//NewReleasePrice Class
//
//@author Jack Murphy - 18254268

import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.states.StateHandler;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class NewPrice extends Price {
    //movie cost 10 per day
    //customer earns 3 loyalty points per rental per day of a movie
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    private static final double DEFAULT_PRICE = 10.0;

    public NewPrice(UserRepoServiceImpl userRepoService) throws IOException, GeoIp2Exception {
        super();
        if (authentication == null) {
            setPrice(10);
            setLoyaltyPoints(17);
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
                setPrice(10);
                setLoyaltyPoints(17);
            } else {
                double x = stateHandler.getCurrentTier().getDiscount();
                double result = 10 * x;
                result = Math.round(result * 100.0) / 100.0;
                setPrice(result);
                setLoyaltyPoints(17);
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
                "price=" + getPriceStr() +
                ", loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }

}
