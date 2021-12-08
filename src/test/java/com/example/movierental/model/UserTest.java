package com.example.movierental.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assert.assertEquals;

public class UserTest {

    private User createUser(){
        User user = new User();

        user.setUserID(12344321);
        user.setUsername("Test User");
        user.setPassword("testPassword");
        user.setBanned(false);
        user.setLoyaltyPoints(34);
        user.setTier(2);
        user.setAdmin(true);

        return user;
    }

    public void testSettersAndGetters(){
        User user = createUser();

        assertEquals(12344321,user.getUserID());
        assertEquals("Test User",user.getUsername());
        assertEquals("testPassword",user.getPassword());
        assertEquals(false,user.isBanned());
        assertEquals(34,user.getLoyaltyPoints());
        assertEquals(2,user.getTier());
        assertEquals(true,user.isAdmin());
    }

    //needs to be updated properly
    public void testToString(){
        User user = createUser();

        asserEquals("User{userID=12344321, username=\"Test User\"}",user.toString());
    }
}
