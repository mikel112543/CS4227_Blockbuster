package com.example.movierental.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assert.assertEquals;

/**
 * PriceFactoryTest Class
 * Author - Jack Murphy - 18254268
 */
public class PriceFactoryTest {

    /**
     * priceCodes
     * 0 = New Release
     * 1 = Standard Release
     * 2 = Childrens Release
     */
    @Test
    public void testPriceFactory(){

        Price p1 = new PriceFactory(0);
        Price p2 = new PriceFactory(1);
        Price p3 = new PriceFactory(2);

        assertTrue(p1 instanceof newReleasePrice);
        assertTrue(p2 instanceof standardReleasePrice);
        assertTrue(p3 instanceof childrensReleasePrice);

    }
}
