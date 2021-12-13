package com.example.movierental;

import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.RentalServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

/**
 * Author - Michael Danaher
 */
@Service
public final class SetUp {

    @Autowired
    UserRepoServiceImpl userService;

    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    RentalServiceImpl rentalService;

    //Will run upon Springboot Start Up
    @PostConstruct
    private void setUp() {
        userService.initializeUsers();
        movieService.initializeList();
        rentalService.checkRentals();
    }
}
