package com.example.movierental.service;

import com.example.movierental.model.Customer;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class RentalServiceImpl implements RentalService {

    //@Autowired
    //UserServiceimpl userService;

    //@Autowired
    //MovieServiceimpl movieService;

/**
     *
     * @param customerId - User renting the movie
     * @param movieId - Movie to be rented
     */

    @Override
    public void rentMovie(int customerId, int movieId) {
        //Find by ID to locate movie and customer
        Customer customer = userService.findbyId(customerId);
        Movie movie = movieService.findbyId(movieId);

        int customerTier = customer.getTier();
        // Check tier of customer
        // Add rent time based on customer tier
        if (customerTier == 1) {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(4));
            //userService.addMovie(customerId, rental);
        } else if (customerTier == 2) {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(8));
            //userService.addMovie(customerId, rental);
        } else {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(16));
            //userService.addMovie(customerId, rental);
        }
    }

/**
     *
     * @param customerId - Customer who owns the rental
     * @param movieId - Movie ID
     */

    public void removeRental(int customerId, int movieId) {
        //Customer customer = userService.findbyId(customerId);
        //ArrayList<Rental> customerMovies =  customer.getUserMovies();
        //Remove rental based off customer and movie ID
        //customerMovies.removeIf(customerMovie -> customerMovie.getMovie().getMovieId() == movieId);
    }
}
