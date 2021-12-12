package com.example.movierental.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assert.assertEquals;

/**
 * Movie Test Class
 * Author - Jack Murphy - 18254268
 */
public class MovieTest {

    /**
     * Method to initalise a movie object for testing
     * @param priceCode, as in PriceFactory
     * @return Movie
     */
    private Movie createMovie(int priceCode){
        return new Movie(1817871, "Test Movie", "Classic Test","Very Test Worthy","1.5",priceCode,4);
    }

    /**
     * To test the values set by the createMovie and the getters in the Movie class return the expected values
     */
    @Test
    public void testGetters(){
        Movie m = createMovie(1);

        assertEquals(1817871,m.getMovieId());
        assertEquals("Test Movie",m.getTitle());
        assertEquals("Classic Test",m.getGenre());
        assertEquals("Very Test Worthy",m.getDescription());
        assertEquals("1.5",m.getLength());
        assertEquals(4,m.getMovieRating());
    }

    /**
     * Testing the getCharge method for movies with different pricing structures and different discounts
     */
    @Test
    public void testGetCharge(){
        Movie m1 = createMovie(0);
        assertEquals(9.00,m1.getCharge(10));
        assertEquals(8.00,m1.getCharge(20));

        Movie m2 = createMovie(2);
        assertEquals(4.00,m2.getCharge(20));
        assertEquals(3.00,m2.getCharge(40));
    }

}
