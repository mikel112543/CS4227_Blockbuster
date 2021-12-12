package com.example.movierental.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assert.assertEquals;


/**
 * UserTest Class
 * Author - Jack Murphy - 18254268
 */
public class PriceTest {

    //Method for creating accounts in example?
    Price newRelease = new newReleasePrice();
    Price standardRelease = new standardReleasePrice();
    Price childrensRelease = new childrensReleasePrice();

    @Test
    public void testGetters(){
        //Testing Prices
        assertEquals(10,newRelease.getPrice());
        assertEquals(8,standardRelease.getPrice());
        assertEquals(5,childrensRelease.getPrice());

        //Testing LoyaltyPoints
        assertEquals(3,newRelease.getLoyaltyPoints());
        assertEquals(2,standardRelease.getLoyaltyPoints());
        assertEquals(1,childrensRelease.getLoyaltyPoints());
    }

    @Test
    public void testGetCharge(){
        assertEquals(9,newRelease.getCharge(10));
        assertEquals(6.4,standardRelease.getCharge(20));
        assertEquals(4,childrensRelease.getCharge(20));
    }

    @Test
    public void testGetLoyaltyPointsEarned(){
        assertEquals(9,newRelease.getLoyaltyPointsEarned(3));
        assertEquals(6,standardRelease.getLoyaltyPointsEarned(3));
        assertEquals(3,childrensRelease.getLoyaltyPointsEarned(3));
    }

    @Test
    public void testToString(){
        assertEquals("newReleasePrice{price=10, loyaltyPoints=3}",newRelease.toString());
        assertEquals("standardReleasePrice{price=10, loyaltyPoints=3}",standardRelease.toString());
        assertEquals("childrensReleasePrice{price=10, loyaltyPoints=3}",childrensRelease.toString());
    }

}
