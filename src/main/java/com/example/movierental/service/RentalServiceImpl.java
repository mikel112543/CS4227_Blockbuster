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


/**
 * Author - Michael Danaher
 */
@Service
public class RentalServiceImpl implements RentalService {

    private static AbstractLogger chainLogger = RequesterClient.getChaining();

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MovieServiceImpl movieService;

    /**
     * @param userId  - User renting the movie
     * @param movieId - Movie to be rented
     */
    @Override
    public Rental rentMovie(int userId, int movieId) {
        //Find by ID to locate movie and customer
        Movie movie;
        User user = userService.findById(userId);
        int customerTier = user.getTier();

        try {
            movie = movieService.findByMovieID(movieId);
        } catch (NumberFormatException e) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find Movie ID");
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
        }
        if (customerTier == 1) {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(4));
            userService.addMovie(userId, rental);
            chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User has rented the movie for 4 days");
            return rental;
        } else if (customerTier == 2) {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(8));
            userService.addMovie(userId, rental);
            chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User has rented the movie for 8 days");
            return rental;
        } else {
            Rental rental = new Rental(movie, LocalDate.now().plusDays(16));
            userService.addMovie(userId, rental);
            chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User has rented the movie for 16 days");
            return rental;
        }
    }

    /**
     * @param userId - Customer who owns the rental
     * @param movieId - Movie ID
     */
    public List<Rental> removeRental(int userId, int movieId) {
        User user = userService.findByID(userId);
        List<Rental> userRentals = user.getRentedMovies();

        if (userRentals.isEmpty()) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "User has no rentals");
            throw new ServiceException(new ServiceError(Error.NO_RENTALS));
        } else {
            for (int i = 0; i < userRentals.size(); i++) {
                if (userRentals.get(i).getMovie().getMovieId() == movieId) {
                    chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Removing Rental");
                    userRentals.remove(i);
                    return userRentals;
                }
            }
        }
        return userRentals;
    }
}
