package com.example.movierental.service;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author - Michael Danaher
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Rental Service Tests")
class RentalServiceImplTest {

    UserRepoServiceImpl userService;
    MovieServiceImpl movieService;
    RentalServiceImpl rentalService;


    @Autowired
    public RentalServiceImplTest(UserRepoServiceImpl userService, MovieServiceImpl movieService, RentalServiceImpl rentalService) {
        this.userService = userService;
        this.movieService = movieService;
        this.rentalService = rentalService;
    }

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        movieService.initializeMovies();
    }

    @AfterTestExecution
    public void tearDown() {
        User testUser = userService.findByID(9);
        rentalService.getRentals(testUser.getUserID()).clear();
        movieService.getMovies().clear();
    }

    @Test
    @DisplayName("Should return Tier 1 user rentals")
    void rentMovieT1() {
        User testUser = userService.findByID(9);
        List<Rental> testRentals = rentalService.rentMovie(testUser.getUserID(), 2);
        assertEquals(1, testRentals.size());
        assertEquals("Transformers", testRentals.get(0).getMovie().getTitle());
        assertEquals("Action", testRentals.get(0).getMovie().getGenre());
        assertEquals("When Alien robots land on earth. There is only one man who can help.", testRentals.get(0).getMovie().getDescription());
        assertEquals("2 hours 24 minutes", testRentals.get(0).getMovie().getLength());
        assertEquals("images/transformers.jpg", testRentals.get(0).getMovie().getMovieCoverUrl());
        assertEquals(3, testRentals.get(0).calculateRemainingDays());
    }

    @Test
    @DisplayName("Should return Tier 2 user rentals")
    void rentMovieT2() {
        User testUser = userService.findByID(10);
        List<Rental> testRentals = rentalService.rentMovie(testUser.getUserID(), 2);
        assertEquals(1, testRentals.size());
        assertEquals("Transformers", testRentals.get(0).getMovie().getTitle());
        assertEquals("Action", testRentals.get(0).getMovie().getGenre());
        assertEquals("When Alien robots land on earth. There is only one man who can help.", testRentals.get(0).getMovie().getDescription());
        assertEquals("2 hours 24 minutes", testRentals.get(0).getMovie().getLength());
        assertEquals("images/transformers.jpg", testRentals.get(0).getMovie().getMovieCoverUrl());
        assertEquals(5, testRentals.get(0).calculateRemainingDays());
    }

    @Test
    @DisplayName("Should return Tier 3 user rentals")
    void rentMovieT3() {
        User testUser = userService.findByID(11);
        List<Rental> testRentals = rentalService.rentMovie(testUser.getUserID(), 2);
        assertEquals(1, testRentals.size());
        assertEquals("Transformers", testRentals.get(0).getMovie().getTitle());
        assertEquals("Action", testRentals.get(0).getMovie().getGenre());
        assertEquals("When Alien robots land on earth. There is only one man who can help.", testRentals.get(0).getMovie().getDescription());
        assertEquals("2 hours 24 minutes", testRentals.get(0).getMovie().getLength());
        assertEquals("images/transformers.jpg", testRentals.get(0).getMovie().getMovieCoverUrl());
        assertEquals(10, testRentals.get(0).calculateRemainingDays());
    }

    @Test
    @DisplayName("Should return Service error for movie already rented")
    void movieAlreadyRented() {
        User testUser = userService.findByID(9);
        Movie testMovie = movieService.findByMovieID(2);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            rentalService.rentMovie(testUser.getUserID(), testMovie.getMovieId());
            rentalService.rentMovie(testUser.getUserID(), testMovie.getMovieId());
        });
        assertEquals("2008", exception.getServiceError().getErrorCode());
        tearDown();
    }

    @Test
    @DisplayName("Should return Service Error for No Rentals")
    void userRentalsEmpty() {
        User testUser = userService.findByID(9);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            rentalService.getRentals(testUser.getUserID());
        });
        assertEquals("2007", exception.getServiceError().getErrorCode());
    }

    @Test
    @DisplayName("Should return the List of users Rentals")
    void getRentals() {
        User testUser = userService.findByID(9);
        rentalService.rentMovie(testUser.getUserID(), 1);
        rentalService.rentMovie(testUser.getUserID(), 2);
        List<Rental> testRentals = rentalService.getRentals(testUser.getUserID());
        assertEquals("Titanic", testRentals.get(0).getMovie().getTitle());
        assertEquals("Historical Drama", testRentals.get(0).getMovie().getGenre());
        assertEquals("Doomed before departure. Titanic explores the relationship between a woman of high class and a " +
                                    "commoner before the infamous tragedy.", testRentals.get(0).getMovie().getDescription());
        assertEquals("2 hours", testRentals.get(0).getMovie().getLength());
        assertEquals("images/titanic.jpg", testRentals.get(0).getMovie().getMovieCoverUrl());

        assertEquals("Transformers", testRentals.get(1).getMovie().getTitle());
        assertEquals("Action", testRentals.get(1).getMovie().getGenre());
        assertEquals("When Alien robots land on earth. There is only one man who can help.", testRentals.get(1).getMovie().getDescription());
        assertEquals("2 hours 24 minutes", testRentals.get(1).getMovie().getLength());
        assertEquals("images/transformers.jpg", testRentals.get(1).getMovie().getMovieCoverUrl());
        tearDown();
    }

    @Test
    @DisplayName("Should return a singular rental")
    void getRental() {
        //Rent movie to set up test
        User testUser = userService.findByID(9);
        Movie testMovie = movieService.findByMovieID(1);
        rentalService.rentMovie(testUser.getUserID(), testMovie.getMovieId());
        List<Rental> rental = rentalService.getRental(testUser.getUserID(), testMovie.getMovieId());
        assertEquals("Titanic", rental.get(0).getMovie().getTitle());
        assertEquals("Historical Drama", rental.get(0).getMovie().getGenre());
        assertEquals("Doomed before departure. Titanic explores the relationship between a woman of high class and a " +
                                    "commoner before the infamous tragedy.", rental.get(0).getMovie().getDescription());
        assertEquals("2 hours", rental.get(0).getMovie().getLength());
        assertEquals("images/titanic.jpg", rental.get(0).getMovie().getMovieCoverUrl());
        tearDown();
    }

    @Test
    @DisplayName("Should remove the movie from the users rentals")
    void removeRental() {
        User testUser = userService.findByID(9);
        Movie testMovie = movieService.findByMovieID(2);
        rentalService.rentMovie(testUser.getUserID(), testMovie.getMovieId());
        String result = rentalService.removeRental(testUser.getUserID(), testMovie.getMovieId());
        assertEquals("User Name: test Rental: Transformers has been removed from their rentals", result);
    }
}