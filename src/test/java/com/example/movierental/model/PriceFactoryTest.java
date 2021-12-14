package com.example.movierental.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceFactoryTest {

    private Price p1, p2, p3;//p4;
    private PriceFactory p;
    /**
     * priceCodes
     * 0 = New Release
     * 1 = Standard Release
     * 2 = Childrens Release
     */
    @BeforeEach
    void setUp() {
        p = new PriceFactory();
        p1 = p.getPrice(0);
        p2 = p.getPrice(1);
        p3 = p.getPrice(2);
        //p4 = p.getPrice(4);
    }

    @AfterEach
    void tearDown() {
        p = null;
        p1 = null;
        p2 = null;
        p3 = null;
        //p4 = null;
    }

    @Test
    void TestGetPrice() {
        assertTrue(p1 instanceof NewPrice);
        assertTrue(p1 instanceof StandardPrice);
        assertTrue(p3 instanceof ChildrensPrice);
    }
}