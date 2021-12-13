package com.example.movierental.service;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author - Michael Danaher
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Rental Service Tests")
class RentalServiceImplTest {

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    RentalServiceImpl rentalService;

    @Autowired
    UserRepoServiceImpl userService;

    @Autowired
    MovieServiceImpl movieService;

    private User testUser;
    private int testMovieId;


    @BeforeEach
    public void setUp() {
        List<Rental> mockRentals = new ArrayList<>();
        testUser = new User(99, "test", "test", "ROLE_USER", false);
        testUser.setRentedMovies(mockRentals);
        testUser.setTier(2);
        testUser.setLoyaltyPoints(700);
        userService.addUser(testUser);
        adminService.addMovie("Test Movie", "Test Genre", "Test Description", 1.23, 1, 4);
        testMovieId = movieService.findByName("Test Movie").get(0).getMovieId();
    }

    @AfterTestExecution
    public void tearDown() {
        rentalService.getRentals(testUser.getUserID()).clear();
        adminService.deleteMovie(testMovieId);
    }

    @Test
    @DisplayName("Should return Service error for movie already rented")
    void movieAlreadyRented() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            rentalService.rentMovie(testUser.getUserID(), testMovieId);
            rentalService.rentMovie(testUser.getUserID(), testMovieId);
        });
        assertEquals("2007", exception.getServiceError().getErrorCode());
    }

    @Test
    @DisplayName("Should return Service Error for No Rentals")
    void userRentalsEmpty() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            rentalService.getRentals(testUser.getUserID());
        });
        assertEquals("2006", exception.getServiceError().getErrorCode());
    }

    @Test
    @DisplayName("Should return the List of users Rentals")
    void getRentals() {
        List<Rental> testRentals = rentalService.getRentals(testUser.getUserID());
        assertEquals("Test Movie", testRentals.get(0).getMovie().getTitle());
        assertEquals("Test Genre", testRentals.get(0).getMovie().getGenre());
        assertEquals("Test Description", testRentals.get(0).getMovie().getDescription());
        assertEquals(1.23, testRentals.get(0).getMovie().getLength());
        assertEquals(4, testRentals.get(0).getMovie().getMovieRating());
    }

    @Test
    @DisplayName("Should return a singular rental")
    void getRental() {
        //Rent movie to set up test
        rentalService.rentMovie(testUser.getUserID(), testMovieId);
        Rental rental = rentalService.getRental(testUser.getUserID(), testMovieId);
        assertEquals("Test Movie", rental.getMovie().getTitle());
        assertEquals("Test Genre", rental.getMovie().getGenre());
        assertEquals("Test Description", rental.getMovie().getDescription());
        assertEquals(1.23, rental.getMovie().getLength());
    }

    @Test
    @DisplayName("Should remove the movie from the users rentals")
    void removeRental() {
        rentalService.rentMovie(testUser.getUserID(), testMovieId);
        String result = rentalService.removeRental(testUser.getUserID(),testMovieId);
        assertEquals("User Name: test Rental: Test Movie has been removed from their rentals", result);
    }
}