package com.example.movierental.controller;

import com.example.movierental.model.Rental;
import com.example.movierental.service.RentalService;
import com.example.movierental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*

@Controller
public class RentalController {

    @Autowired
    RentalService rentalService;

    @Autowired
    UserService userService;


*/
/*
    *//*
*/
/**
     * @param customerId - Customer ID wanting the rent the movie
     * @param movieId    - Movie ID to be rented
     * @return User.html
     *//*

    @PostMapping(value = "/movies/")
    public String rentMovieView(@PathVariable("MOVIE_ID") final int movieId) {

        rentalService.rentMovie(customerId, movieId);
        return "user";

    }

    */
/**
     * @return User.html
     *//*

    @GetMapping(value = "/customerId/{CUSTOMER_ID}/rentals")
    public List<Rental> showRentals(@PathVariable("CUSTOMER_ID") final int customerID) {
        return rentalService.showRentals(customerID);
       */
/* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) auth.getPrincipal();*//*

        //int customerId = customer.getUserID();
        //List<Rental> userRentals = userService.getUserRentals(customerId);
        //model.addAttribute("userRentals", userRentals); //Attach List of rentals to model to be used in Thymeleaf
    }

    */
/**
     *
     * @param customerId - Customer ID who owns the rental
     * @param movieId - Movie ID of currently rented movie
     * @return JSON Object
     *//*

    @GetMapping(value = "/customerId/{CUSTOMER_ID}/rentals/{MOVIE_ID}")
    public Rental getRental(@PathVariable("CUSTOMER_ID") final int customerId,
                                 @PathVariable("MOVIE_ID") final int movieId) {

        return userService.getRental(customerId, movieId);
        //Attach rental to model to be used in Thymeleaf
    }

   */
/* *//*
*/
/**
     * @param customerId - the ID of the customer who owns the rental
     * @param movieId    - Unique identifier for the movie
     * @return User.html
     *//*

    @DeleteMapping(value = "/admin/removeRental/customer/{CUSTOMER_ID}/{RENTAL_ID}/")
    public String removeRentalView(@PathVariable("RENTAL_ID") final int rentalId,
                                   @PathVariable("CUSTOMER_ID") final int customerId) {

        return rentalService.removeRental(customerId, rentalId);

    }
}*/
