package com.example.movierental.controllers;

import com.example.movierental.model.Customer;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import com.example.movierental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RentalController {

/*    @Autowired
    RentalService rentalService;

    @Autowired
    UserService userService;

    @Autowired
    PriceService priceService;

    @Autowired
    Authentication auth;*/


/*
    *//**
     * @param customerId - Customer ID wanting the rent the movie
     * @param movieId    - Movie ID to be rented
     * @return User.html
     */
    @PostMapping(value = "/movies/")
    public String rentMovieView(@PathVariable("MOVIE_ID") final int movieId) {

        rentalService.rentMovie(customerId, movieId);
        return "user";

    }

    /**
     * @return User.html
     */
    @GetMapping(value = "/user")
    public String showRentalsView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) auth.getPrincipal();
        //int customerId = customer.getUserID();
        //List<Rental> userRentals = userService.getUserRentals(customerId);
        //model.addAttribute("userRentals", userRentals); //Attach List of rentals to model to be used in Thymeleaf
        return "user";
    }

    @GetMapping(value = "/")

/*    *//**
     * @param customerId the ID of the customer who owns the rental
     * @param movieId    Unique identifier for the movie
     * @return User.html
     *//*
    @GetMapping(value = "/customerId/{CUSTOMER_ID}/rentals/{MOVIE_ID}")
    public String showRentalView(@PathVariable("CUSTOMER_ID") final int customerId,
                                 @PathVariable("MOVIE_ID") final int movieId,
                                 Model model) {

        Rental rental = userService.getRental(customerId, movieId);
        model.addAttribute("rental", rental); //Attach rental to model to be used in Thymeleaf

        return "rental";
    }*/

   /* *//**
     * @param customerId - the ID of the customer who owns the rental
     * @param movieId    - Unique identifier for the movie
     * @return User.html
     */
    @DeleteMapping(value = "/admin/removeRental/customer/{CUSTOMER_ID}/{RENTAL_ID}/")
    public String removeRentalView(@PathVariable("RENTAL_ID") final int rentalId,
                                   @PathVariable("CUSTOMER_ID") final int customerId) {

        rentalService.removeRental(customerId, rentalId);
        return "user";

    }
}