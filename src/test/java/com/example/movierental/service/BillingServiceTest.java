package com.example.movierental.service;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.model.Bill;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Bill Service Tests")
class BillingServiceTest {

    @Autowired
    UserRepoServiceImpl userService;
    @Autowired
    RentalServiceImpl rentalService;
    @Autowired
    MovieServiceImpl movieService;
    @Autowired
    BillingServiceImpl billingService;

    @BeforeEach
    void setUp(){
        movieService.initializeMovies();
    }

    @AfterTestExecution
    public void tearDown(){
        User testUser = userService.findByID(9);
        rentalService.getRentals(testUser.getUserID()).clear();
        movieService.getMovies().clear();
    }


    @Test
    @DisplayName("ListOfBills should contain a bill with testUser renting titanic")
    void createBill(){
        User testUser = userService.findByID(9);
        Movie testMovie = movieService.findByMovieID(1);
        List<Rental> testRentals = rentalService.rentMovie(5, 1);
        List<Bill> testBills = billingService.getBills();

        assertEquals(1, testRentals.size());
        assertEquals(1, testBills.size());
        assertEquals(1, testBills.get(0).getBillId());
        Rental testRental = testBills.get(0).getRental();
        assertEquals("Titanic", testRental.getMovie().getTitle());
        assertEquals("test123@gmail.com", testUser.getEmailAddress());
    }


}
