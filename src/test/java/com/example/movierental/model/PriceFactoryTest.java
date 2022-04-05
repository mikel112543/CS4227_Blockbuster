package com.example.movierental.model;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.service.UserRepoServiceImpl;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PriceFactoryTest Class
 * Author - Jack Murphy 18254268
 */
class PriceFactoryTest {

    private Price p1, p2, p3, p4;
    private PriceFactory p;

    @Autowired
    UserRepoServiceImpl userRepoService;
    /**
     * priceCodes
     * 0 = New Release
     * 1 = Standard Release
     * 2 = Childrens Release
     */
    @BeforeEach
    void setUp() throws IOException, GeoIp2Exception {
        p1 = PriceFactory.getPrice(0, userRepoService);
        p2 = PriceFactory.getPrice(1, userRepoService);
        p3 = PriceFactory.getPrice(2, userRepoService);
    }

    @AfterEach
    void tearDown() {
        p = null;
        p1 = null;
        p2 = null;
        p3 = null;
        p4 = null;
    }

    @Test
    @DisplayName("Testing Price Factory Returns the Correct Price Class Based on Price Code")
    void testGetPrice() {
        assertTrue(p1 instanceof NewPrice);
        assertTrue(p2 instanceof StandardPrice);
        assertTrue(p3 instanceof ChildrensPrice);
    }

    @Test
    @DisplayName("Should Return a ServiceError if Incorrect PriceCode Entered")
    void testWrongPriceCode(){
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            p4 = p.getPrice(5, userRepoService);
        });
        assertEquals("2013",exception.getServiceError().getErrorCode());
    }
}