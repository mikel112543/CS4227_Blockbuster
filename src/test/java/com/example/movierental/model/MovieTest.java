package com.example.movierental.model;

import com.example.movierental.model.Movie.MovieBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * MovieTest Class
 * Author - Jack Murphy 18254268
 */
class MovieTest {

   /* private Movie testMovie1, testMovie2;
    private MovieBuilder testMovieBuilder;

    @BeforeEach
    void setUp() {
        //testMovieBuilder = new MovieBuilder("Test Movie One","Genre NewPrice","Described as \"Good for Testing\"","1.1",11111,"Test1.jpg");
        testMovieBuilder.setPrice(0);
        testMovie1 = testMovieBuilder.build();

        //testMovieBuilder = new MovieBuilder("Test Movie Two","Genre ChildrensPrice","Described as \"Grand for Testing\"","1.3",333333,"Test2.jpg");
        testMovieBuilder.setPrice(2);
        testMovie2 = testMovieBuilder.build();
    }

    @AfterEach
    void tearDown() {
        testMovie1 = null;
        testMovie2 = null;
        testMovieBuilder = null;
    }

    @Test
    @DisplayName("Testing Builder with Getters")
    void testBuilder(){
        assertEquals("Test Movie One",testMovie1.getTitle());
        assertEquals("Genre NewPrice",testMovie1.getGenre());
        assertEquals("Described as \"Good for Testing\"",testMovie1.getDescription());
        assertEquals("1.1",testMovie1.getLength());
        assertEquals(11111,testMovie1.getMovieId());
        assertEquals("Test1.jpg",testMovie1.getMovieCoverUrl());
        assertTrue(testMovie1.getPrice() instanceof NewPrice);

        assertEquals("Test Movie Two",testMovie2.getTitle());
        assertEquals("Genre ChildrensPrice",testMovie2.getGenre());
        assertEquals("Described as \"Grand for Testing\"",testMovie2.getDescription());
        assertEquals("1.3",testMovie2.getLength());
        assertEquals(333333,testMovie2.getMovieId());
        assertEquals("Test2.jpg",testMovie2.getMovieCoverUrl());
        assertTrue(testMovie2.getPrice() instanceof ChildrensPrice);

    }

    @Test
    @DisplayName("Testing getCharge method(s)")
    void testGetCharge(){

        int tierTooHigh = 100;
        int tierTooLow = -1;

        //Testing no parameter getCharge
        assertEquals(10,testMovie1.getCharge());
        assertEquals(5,testMovie2.getCharge());

        //Tier too low
        assertEquals(10,testMovie1.getCharge(-91));
        assertEquals(5,testMovie2.getCharge(-91));

        //Tier 1
        assertEquals(10,testMovie1.getCharge(1));
        assertEquals(5,testMovie2.getCharge(1));

        //Tier 2
        assertEquals(9,testMovie1.getCharge(2));
        assertEquals(4.5,testMovie2.getCharge(2));

        //Tier 3
        assertEquals(8,testMovie1.getCharge(3));
        assertEquals(4,testMovie2.getCharge(3));

        //Tier too high
        assertEquals(8,testMovie1.getCharge(35));
        assertEquals(4,testMovie2.getCharge(35));

    }

    @Test
    @DisplayName("Testing getChargeString method")
    void testGetChargeString(){

        //Tier too low
        assertEquals("Movie Price: €5.0\n" +
                "User Tier: 0, No discount applied\n" +
                "Charge for Rental: €5.0",testMovie2.getChargeString(-1));
        //Tier1
        assertEquals("Movie Price: €10.0\n" +
                "User Tier: 1, No discount applied\n" +
                "Charge for Rental: €10.0",testMovie1.getChargeString(1));
        //Tier2
        assertEquals("Movie Price: €5.0\n" +
                "User Tier: 2, Discount applied\n" +
                "Charge for Rental: €4.5",testMovie2.getChargeString(2));

        //Tier3
        assertEquals("Movie Price: €10.0\n" +
                "User Tier: 3, Discount applied\n" +
                "Charge for Rental: €8.0",testMovie1.getChargeString(3));
        //Tier too high
        assertEquals("Movie Price: €5.0\n" +
                "User Tier: 3, Discount applied\n" +
                "Charge for Rental: €4.0",testMovie2.getChargeString(200));

    }*/
}