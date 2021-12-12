package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author - Michael Danaher
 */
@Service
public class RentalServiceImpl implements RentalService {

    private static AbstractLogger chainLogger = RequesterClient.getChaining();

    private final Timer timer = new Timer();


    @Autowired
    UserServiceImpl userService;

    @Autowired
    MovieServiceImpl movieService;

    /**
     * @param userId  - User renting the movie
     * @param movieId - Movie to be rented
     */
    @Override
    public List<Rental> rentMovie(int userId, int movieId) {
        //Check Movie ID and User ID
        Movie movie = movieService.findByMovieID(movieId);
        User user = userService.findByID(userId);
        int lp = movie.getPrice().getLoyaltyPoints();

        List<Rental> userRentals = user.getRentedMovies();
        int customerTier = user.getTier();

        //Check if user has already rented movie
        for (Rental userRental : userRentals) {
            chainLogger.logMessage(AbstractLogger.DEBUG_INFO, "Checking if user has already rented the movie");
            if (userRental.getMovie().getMovieId() == movieId) {
                chainLogger.logMessage(AbstractLogger.ERROR_INFO, "User is already currently renting this movie");
                throw new ServiceException(new ServiceError(Error.ALREADY_RENTING));
            }
        }
        //Apply discount for Tier 1
        chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User is getting " + lp + " Loyalty Points");
        if (customerTier == 1) {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(4));
            chainLogger.logMessage(AbstractLogger.DEBUG_INFO, "Adding Movie to users catalog");
            userRentals.add(rental);
            //Add loyalty points to users account
            user.setLoyaltyPoints(user.getLoyaltyPoints() + lp);
            chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User has rented the movie for 4 days");
            return user.getRentedMovies();
            //Apply discount for Tier 2
        } else if (customerTier == 2) {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(6));
            chainLogger.logMessage(AbstractLogger.DEBUG_INFO, "Adding Movie to users catalog");
            userRentals.add(rental);
            //Add loyalty points to users account
            user.setLoyaltyPoints(user.getLoyaltyPoints() + lp);
            chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User has rented the movie for 6 days");
            return user.getRentedMovies();
        } else {
            //Apply discount for Tier 3
            Rental rental = new Rental(movie, LocalDate.now().plusDays(10));
            userRentals.add(rental);
            //Add loyalty points to users account
            user.setLoyaltyPoints(user.getLoyaltyPoints() + lp);
            chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User has rented the movie for 10 days");
            return user.getRentedMovies();
        }
    }

    /**
     * @param userId - Unique Identifier for User
     * @return JSON list of user rented movies
     */
    public List<Rental> getRentals(int userId) {
        //Check User ID
        User user = userService.findByID(userId);
        //Check if user has no rentals
        if (user.getRentedMovies().isEmpty()) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "User has no rentals");
            throw new ServiceException(new ServiceError(Error.NO_RENTALS));
        } else {
            return user.getRentedMovies();
        }
    }

    /**
     * @param userId  - Unique Identifier for User
     * @param movieId -Unique Identifier for Movie
     * @return JSON Rental
     */
    public Rental getRental(int userId, int movieId) {
        //Checks User ID
        User user = userService.findByID(userId);
        List<Rental> userRentals = user.getRentedMovies();
        //Check if user has no rentals
        if (userRentals.isEmpty()) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "User has no rentals");
            throw new ServiceException(new ServiceError(Error.NO_RENTALS));
        } else {
            //Checking for rental in users catalog
            for (Rental userRental : userRentals) {
                if (userRental.getMovie().getMovieId() == movieId) {
                    chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Retrieving Rental...");
                    return userRental;
                }
            }
            //Last possible option, invalid movieID
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find movie");
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
        }
    }

    /**
     * @param userId  - Customer who owns the rental
     * @param movieId - Movie ID
     */
    public String removeRental(int userId, int movieId) {
        //Checks User ID
        User user = userService.findByID(userId);
        List<Rental> userRentals = user.getRentedMovies();

        //Checks if rentals are empty
        if (userRentals.isEmpty()) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "User has no rentals");
            throw new ServiceException(new ServiceError(Error.NO_RENTALS));
        } else {
            for (Rental userRental : userRentals) {
                if (userRental.getMovie().getMovieId() == movieId) {
                    chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Removing Rental...");
                    userRentals.remove(userRental);
                    return "User Name: " + user.getUsername() + " Rental: " + userRental.getMovie().getTitle() +
                            " has been removed from their rentals";
                }
            }
            //Last possible check for Movie ID
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find movie");
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
        }
    }

    /**
     * Routinely checks expiry of all users rentals
     */
    @Override
    public void checkRentals() {
        List<User> users = userService.getUsers();
        TimerTask checkRentals = new TimerTask() {
            @Override
            public void run() {
                //Loop to get users
                chainLogger.logMessage(AbstractLogger.DEBUG_INFO, "Checking users rentals...");
                for (User user : users) {
                    List<Rental> userRentals = user.getRentedMovies();
                    //Loop to get users rentals
                    chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Removing expired rentals...");
                    for (Rental rental : userRentals) {
                        if (rental.calculateRemainingDays() == 0) {
                            removeRental(user.getUserID(), rental.getMovie().getMovieId());
                        }
                    }
                }
            }
        };
        //Set timer to run check every 5 hours
        timer.schedule(checkRentals, 0L, 1000L *60*60*60*60*60*60);
    }
}
