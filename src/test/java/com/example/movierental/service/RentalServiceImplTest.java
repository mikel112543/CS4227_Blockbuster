package com.example.movierental.service;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Rental Service Tests")
class RentalServiceImplTest {

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    RentalServiceImpl rentalService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MovieServiceImpl movieService;

    private User testUser;
    private int testMovieId;


    @BeforeEach
    public void setUp() {
        List<Rental> mockRentals = new ArrayList<>();
        testUser = new User(99, "test", "test", false, 26, 2, mockRentals, false);
        userService.addUser(testUser);
        adminService.addMovie("Test Movie", "Test Genre", "Test Description", 1.23, 1, 4);
        testMovieId = movieService.findByName("Test Movie").get(0).getMovieId();
    }

    @AfterTestExecution
    public void tearDown() {
        rentalService.getRentals(testUser.getUserID()).clear();
    }

    @Test
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
        Rental mockRental = rentalService.getRental(testUser.getUserID(), testMovieId);
        assertEquals("Test Movie", mockRental.getMovie().getTitle());
        assertEquals("Test Genre", mockRental.getMovie().getGenre());
        assertEquals("Test Description", mockRental.getMovie().getDescription());
        assertEquals(1.23, mockRental.getMovie().getLength());
        assertEquals(4, mockRental.getMovie().getMovieRating());
    }

    @Test
    @DisplayName("Should return a singular rental")
    void getRental() {
        //Set up so there is rental in users account
        List<Rental> mockRentals = rentalService.rentMovie(testUser.getUserID(), testMovieId);
        Rental rental = rentalService.getRental(testUser.getUserID(), testMovieId);
        assertEquals("Test Movie", rental.getMovie().getTitle());
        assertEquals("Test Genre", rental.getMovie().getGenre());
        assertEquals("Test Description", rental.getMovie().getDescription());
        assertEquals(1.23, rental.getMovie().getLength());
    }

    @Test
    void removeRental() {
    }
}