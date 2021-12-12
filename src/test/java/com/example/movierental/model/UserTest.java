package com.example.movierental.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assert.assertEquals;

/**
 * UserTest Class
 * Author - Jack Murphy - 18254268
 */
public class UserTest {

    /**
     * Creates a User to be tested
     * @return User
     */
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

    /**
     * Test that the users getters match the values set by the setters in createUser
     */
    @Test
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

    /**
     * Tests the toString() matches expected
     */
    @Test
    public void testToString(){
        User user = createUser();

        asserEquals("User{userID=12344321, username='Test User'}",user.toString());
    }
}
