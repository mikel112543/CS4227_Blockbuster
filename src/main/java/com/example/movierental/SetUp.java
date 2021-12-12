package com.example.movierental;

import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.RentalServiceImpl;
import com.example.movierental.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

/**
 * Author - Michael Danaher
 */
@Service
public final class SetUp {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    RentalServiceImpl rentalService;

    //Will run upon Springboot Start Up
    @PostConstruct
    private void setUp() {
        userService.initializeList();
        movieService.initializeList();
        //rentalService.initializeList();
        rentalService.checkRentals();
    }
}
