package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.*;
import com.example.movierental.states.StateHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    BillingServiceImpl billingService;
    UserRepoServiceImpl userService;
    MovieServiceImpl movieService;
    ObjectMapper mapper;

    @Autowired
    public RentalServiceImpl(UserRepoServiceImpl userService, MovieServiceImpl movieService, BillingServiceImpl billingService, ObjectMapper mapper) {
        this.userService = userService;
        this.movieService = movieService;
        this.billingService = billingService;
        this.mapper = mapper;
    }

    /**
     * Initializes listOfRentals list from csv
     */
    @Override
    public void initializeRentals(){
        String path = "rentals.csv";
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null){
                String [] values = line.split(",");
                Movie movie = movieService.findByMovieID(Integer.parseInt(values[0]));
                User user = userService.findByID(Integer.parseInt(values[1]));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(values[2], formatter);

                Rental rental = new Rental(movie, dateTime);
                user.getRentedMovies().add(rental);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userId  - User renting the movie
     * @param movieId - Movie to be rented
     */
    @Override
    public List<Rental> rentMovie(int userId, int movieId) {
        //Check Movie ID and User ID
        Movie movie = movieService.findByMovieID(movieId);
        User user = userService.findByID(userId);
        int lp = movie.getPriceObj().getLoyaltyPoints();

        List<Rental> userRentals = user.getRentedMovies();

        //Check if user has already rented movie
        chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User is getting " + lp + "Loyalty Points");
        if (!userRentals.isEmpty()) {
            for (Rental userRental : userRentals) {
                if (userRental.getMovie().getMovieId() == movieId) {
                    chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User is already currently renting this movie");
                    throw new ServiceException(new ServiceError(Error.ALREADY_RENTING));
                }
            }
        }
        StateHandler stateHandler = new StateHandler(user);
        Rental rental = new Rental(movie, LocalDateTime.now().plusDays(stateHandler.getCurrentTier().getDays()));
        chainLogger.logMessage(AbstractLogger.DEBUG_INFO, "Adding Movie to users catalog");
        userRentals.add(rental);
        //Adds loyalty points to users account
        user.setLoyaltyPoints(user.getLoyaltyPoints() + lp);
        chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "User has rented the movie for 3 days");
        userService.findByID(userId).stateCheck();
        //create bill
        billingService.createBill(userId, rental);
        return userRentals;
    }

    /**
     * @param userId - Unique Identifier for User
     * @return JSON list of user rented movies
     */
    @Override
    public List<Rental> getRentals(int userId) {
        //Check User ID
        User user = userService.findByID(userId);
        //Check if user has no rentals
        if (user.getRentedMovies().isEmpty()) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "User has no rentals");
            throw new ServiceException(new ServiceError(Error.NO_RENTALS));
        }
        return user.getRentedMovies();
    }

    /**
     * @param userId  - Unique Identifier for User
     * @param movieId -Unique Identifier for Movie
     * @return JSON Rental
     */
    @Override
    public List<Rental> getRental(int userId, int movieId) {
        //Checks User ID
        List<Rental> userRental = new ArrayList<>();
        User user = userService.findByID(userId);
        List<Rental> userRentals = user.getRentedMovies();
        //Check if user has no rentals
        if (userRentals.isEmpty()) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "User has no rentals");
            throw new ServiceException(new ServiceError(Error.NO_RENTALS));
        } else {
            //Checking for rental in users catalog
            for (Rental rental : userRentals) {
                if (rental.getMovie().getMovieId() == movieId) {
                    chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Retrieving Rental...");
                    userRental.add(rental);
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
    @Override
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
     * Routinely checks expiry of all users rentals and Initializes the data
     */

    @Override
    @PostConstruct
    public void checkRentals() throws IOException {
        userService.initializeUsers();
        movieService.initializeMovies();
        initializeRentals();
        TimerTask checkRentals = new TimerTask() {
            @Override
            public void run() {
                List<User> users = userService.getUsers();
                //Loop to get users
                chainLogger.logMessage(AbstractLogger.DEBUG_INFO, "Checking users rentals...");
                for (User user : users) {
                    List<Rental> userRentals = user.getRentedMovies();
                    //Loop to get users rentals
                    for (Rental rental : userRentals) {
                        if (rental.calculateRemainingDays() == 0) {
                            removeRental(user.getUserID(), rental.getMovie().getMovieId());
                        }
                    }
                }
                chainLogger.logMessage(AbstractLogger.OUTPUT_INFO, "Removing expired rentals...");
            }
        };
        movieService.clearMovies();
        //Set timer to run check every 5 hours
        timer.schedule(checkRentals, 0L, 1000L * 60 * 60 * 60 * 60 * 60 * 60);
    }

    /**
     *
     * @param userRentals - List of users Rentals
     * @return Cleaned up List for JSON use
     */
    @Override
    public List<ObjectNode> parseRentals(List<Rental> userRentals) {
        List<ObjectNode> movieNodes = new ArrayList<>();
        chainLogger.logMessage(AbstractLogger.OUTPUT_INFO,"Parsing into clean JSON");
        for (Rental rental : userRentals) {
            ObjectNode movieNode = mapper.createObjectNode();
            movieNode.put("Title", rental.getMovie().getTitle());
            movieNode.put("Genre", rental.getMovie().getGenre());
            movieNode.put("Description", rental.getMovie().getDescription());
            movieNode.put("Length", rental.getMovie().getLength());
            movieNode.put("Price", rental.getMovie().getCharge());
            movieNode.put("Rent Length", rental.calculateRemainingDays());
            movieNodes.add(movieNode);
        }
        return movieNodes;
    }

}
