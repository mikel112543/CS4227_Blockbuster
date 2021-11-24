package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Author - Michael Danaher
 */
@Service
public class RentalServiceImpl implements RentalService {

    //@Autowired
    //UserServiceimpl userService;

    //@Autowired
    //MovieServiceimpl movieService;

    private static AbstractLogger chainLogger = RequesterClient.getChaining();


/**
     *
     * @param customerId - User renting the movie
     * @param movieId - Movie to be rented
     */

    @Override
    public Rental rentMovie(int customerId, int movieId) {
        //Find by ID to locate movie and customer
        Customer customer = userService.findbyId(customerId);
        Movie movie = movieService.findbyId(movieId);
        int customerTier = customer.getTier();

        if(movie == null) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO,"Could not find the Movie");
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
            //Throw Exception
            //Write some arbitrary logger stuff
        }else if(customer == null) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find the customer");
            throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
            //Throw Exception
            //Write some arbitrary logger stuff
        }else {
            chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Checking the customer tier");
            // Check tier of customer
            // Add rent time based on customer tier
            if (customerTier == 1) {
                Rental rental = new Rental(movie, LocalDate.now().plusDays(4));
                //userService.addMovie(customerId, rental);
                chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Rental for Tier One customer ready");
                return rental;
            } else if (customerTier == 2) {
                Rental rental = new Rental(movie, LocalDate.now().plusDays(8));
                //userService.addMovie(customerId, rental);
                chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Rental for Tier Two customer ready");
                return rental;
            } else {
                Rental rental = new Rental(movie, LocalDate.now().plusDays(16));
                //userService.addMovie(customerId, rental);
                chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Rental for Tier Three customer ready");
                return rental;
            }
        }
    }

    /**
     *
     * @param customerId - Customer ID to show rentals for
     * @return - JSON Object
     */
    @Override
    public List<Rental> showRentals(int customerId) {
        List<Rental> customerRentals = new ArrayList<>();
        if(userService.findById(customerId) == null) {
            //Throw Exception
            //Write some arbitrary Logger stuff...
        }else{
            customerRentals = userService.getCustomerMovies(customerId);
        }
        if(customerRentals.size() == 0) {
            //Throw Exception
            //Write some arbitrary logger stuff...
        }
        return customerRentals;
    }

    /**
     *
     * @param customerId - Customer who owns the rental
     * @param movieId - Movie ID
     */
    public String removeRental(int customerId, int movieId) {
        if(userService.findById(customerId) == null) {
            //Throw Exception
            //Write some arbitrary logger stuff
        }else if(movieService.findById(movieId) == null) {
            //Throw Exception
            //Write some arbitrary logger stuff
        }else{
            return userService.removeMovie(customerId, movieId);
        }
        //Customer customer = userService.findbyId(customerId);
        //ArrayList<Rental> customerMovies =  customer.getUserMovies();
        //Remove rental based off customer and movie ID
        //customerMovies.removeIf(customerMovie -> customerMovie.getMovie().getMovieId() == movieId);
    }
}
