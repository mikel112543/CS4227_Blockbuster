package com.example.movierental.controller;

import com.example.movierental.service.RentalService;
import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.states.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param customerId - Customer ID wanting the rent the movie
     * @param movieId    - Movie ID to be rented
     * @return User.html
     */

    @PostMapping(value = "/customerId/{CUSTOMER_ID}/movieId/{MOVIE_ID}")
    public List<Rental> rentMovie(@PathVariable("CUSTOMER_ID") final String customerId,
                                  @PathVariable("MOVIE_ID") final String movieId) {

        int userId = Integer.parseInt(customerId);
        int filmId = Integer.parseInt(movieId);

        return rentalService.rentMovie(userId, filmId);
    }

    /**
     * @param customerId - Customer ID who owns the rental
     * @return JSON Object
     */

    @GetMapping(value = "/customerId/{CUSTOMER_ID}/rentals")
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
        //Attach rental to model to be used in Thymeleaf
    }

    /**
     * @param customerId - the ID of the customer who owns the rental
     * @param rentalId   - Unique identifier for the movie
     * @return JSON Object
     */
    @DeleteMapping(value = "/admin/removeRental/customerId/{CUSTOMER_ID}/{RENTAL_ID}/")
    public List<Rental> removeRentalView(@PathVariable("RENTAL_ID") final String rentalId,
                                         @PathVariable("CUSTOMER_ID") final String customerId) {
        int userId = Integer.parseInt(customerId);
        int rentId = Integer.parseInt(rentalId);

        return rentalService.removeRental(userId, rentId);

    }
}
