package com.example.movierental.controller;

import com.example.movierental.model.User;
import com.example.movierental.service.RentalService;
import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Author - Michael Danaher
 */
@RestController
public class RentalController {

    RentalService rentalService;
    UserRepoServiceImpl userService;

    @Autowired
    public RentalController(RentalService rentalService, UserRepoServiceImpl userService) {
        this.rentalService = rentalService;
        this.userService = userService;
    }

    /**
     *
     * @return - Returns list of logged in users rentals
     */
    @GetMapping(value = "/rentals")
    @ResponseBody
    public List<Rental> showRentals() {
        int userId = 0;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        for(User user : userService.getUsers()) {
            if(Objects.equals(userName, user.getUsername())) {
                userId = user.getUserID();
                break;
            }
        }
        return rentalService.getRentals(userId);
    }

    /**
     * @param customerId - Customer ID wanting the rent the movie
     * @param movieId    - Movie ID to be rented
     * @return User.html
     */

    @PostMapping(value = "/customerId/{CUSTOMER_ID}/movieId/{MOVIE_ID}")
    @ResponseBody
    public List<Rental> rentMovie(@PathVariable("CUSTOMER_ID") final int customerId,
                                  @PathVariable("MOVIE_ID") final int movieId) {


        return rentalService.rentMovie(customerId, movieId);
    }

    /**
     * @param customerId - Customer ID who owns the rental
     * @return JSON Object
     */

    @GetMapping(value = "/customerId/{CUSTOMER_ID}/rentals")
    @ResponseBody
    public List<Rental> showRentals(@PathVariable("CUSTOMER_ID") final String customerId) {
        int userId = Integer.parseInt(customerId);

        return rentalService.getRentals(userId);
    }

    /**
     * @param customerId - Customer ID who owns the rental
     * @param movieId    - Movie ID of currently rented movie
     * @return JSON Object
     */

    @GetMapping(value = "/customerId/{CUSTOMER_ID}/rentals/{MOVIE_ID}")
    public Rental getRental(@PathVariable("CUSTOMER_ID") final int customerId,
                            @PathVariable("MOVIE_ID") final int movieId) {

        return rentalService.getRental(customerId, movieId);
    }

    /**
     * @param customerId - the ID of the customer who owns the rental
     * @param movieId    - Unique identifier for the movie
     * @return JSON Object
     */
    @DeleteMapping(value = "/customerId/{CUSTOMER_ID}/removeRental/{MOVIE_ID}")
    public String removeRental(@PathVariable("CUSTOMER_ID") final int customerId,
                               @PathVariable("MOVIE_ID") final int movieId) {

        return rentalService.removeRental(customerId, movieId);
    }
}
