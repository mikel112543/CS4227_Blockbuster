package com.example.movierental.controller;

import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import com.example.movierental.service.RentalServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import com.example.movierental.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author - Michael Danaher
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepoServiceImpl userService;

    @Autowired
    RentalServiceImpl rentalService;

    @BeforeEach
    void setUp() {
        List<Rental> testRentals = new ArrayList<>();
        User test = new User(99, "test", "test", false, 680, 3, testRentals, false);
        userService.addUser(test);
    }

    @AfterEach
    void tearDown() {
        User user = userService.findByID(99);
        user.getRentedMovies().clear();
    }

    @Test
    @DisplayName("POST to add movie to users rentals")
    void rentMovie() throws Exception {
        mockMvc.perform(post("/customerId/1/movieId/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Movie").exists())
                .andExpect(jsonPath("$[0].Movie.Title", is("Transformers")))
                .andExpect(jsonPath("$[0].Movie.Genre", is("Action")))
                .andExpect(jsonPath("$[0].Movie.Price", is("€8")));
    }

    @Test
    @DisplayName("GET to retrieve all users rentals")
    void showRentals() throws Exception {
        //Simply adding rental of Transformers to test users account
        //Simply adding rental of Pirates of the Caribbean to test users account
        rentalService.rentMovie(99, 1);
        rentalService.rentMovie(99, 2);
        mockMvc.perform(get("/customerId/99/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].Movie").exists())
                .andExpect(jsonPath("$[0].Movie.Title", is("Transformers")))
                .andExpect(jsonPath("$[0].Movie.Genre", is("Action")))
                .andExpect(jsonPath("$[0].Movie.Price", is("€8")))
                .andExpect(jsonPath("$[1].Movie").exists())
                .andExpect(jsonPath("$[1].Movie.Title", is("Pirates of the Caribbean")))
                .andExpect(jsonPath("$[1].Movie.Genre", is("Action")))
                .andExpect(jsonPath("$[1].Movie.Price", is("€8")));
    }

    @Test
    @DisplayName("Testing Controller to retrieve User specified rental")
    void getRental() throws Exception {
        //Renting Pirates of the Caribbean to setup Test
        rentalService.rentMovie(99, 2);
        mockMvc.perform(get("/customerId/99/rentals/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Movie").exists())
                .andExpect(jsonPath("$.Movie.Title", is("Pirates of the Caribbean")))
                .andExpect(jsonPath("$.Movie.Genre", is("Action")))
                .andExpect(jsonPath("$.Movie.Price", is("€8")));
    }

    @Test
    @DisplayName("DELETE to remove movie from users rentals")
    void removeRentalView() throws Exception {
        //Simply renting Pirates of the Caribbean to set up user test
        //movieId == 2
        rentalService.rentMovie(99, 2);
        mockMvc.perform(delete("/customerId/99/removeRental/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("User Name: test Rental: Pirates of the Caribbean has been removed from their rentals"));
    }
}