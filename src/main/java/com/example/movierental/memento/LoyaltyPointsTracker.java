package com.example.movierental.memento;

import com.example.movierental.model.User;
import com.example.movierental.service.UserRepoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoyaltyPointsTracker implements java.io.Serializable {
    private int loyaltyPoints;
    private int previousPoints;

    UserRepoServiceImpl userRepoService;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    public LoyaltyPointsTracker(UserRepoServiceImpl userRepoService) {
        loyaltyPoints = userRepoService.findByUserName(authentication.getName()).getLoyaltyPoints();
        previousPoints = userRepoService.findByUserName(authentication.getName()).getLoyaltyPoints();
    }

    //store original loyalty points and add new points
    public void setLoyaltyPoints(int points) {
        previousPoints = loyaltyPoints;
        loyaltyPoints += points;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    //For testing
    public int getPreviousPoints() {
        return previousPoints;
    }
}
