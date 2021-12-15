package com.example.movierental.controller;

import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.RentalServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
@WithMockUser(username = "test") // 1Lp Tier1
class RentalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepoServiceImpl userService;

    @Autowired
    RentalServiceImpl rentalService;

    @Autowired
    MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        movieService.initializeMovies();

    }

    @AfterEach
    void tearDown() {
        User user = userService.findByID(9);
        user.getRentedMovies().clear();
        movieService.getMovies().clear();
    }

    @Test
    @DisplayName("GET to display logged in users rentals")
    void showLoggedInRentals() throws Exception {
        rentalService.rentMovie(9, 2);
        mockMvc.perform(get("/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Title", is("Transformers")))
                .andExpect(jsonPath("$[0].Genre", is("Action")))
                .andExpect(jsonPath("$[0].Price", is(8.0)));
        tearDown();
    }

    @Test
    @DisplayName("POST to add movie to users rentals")
    void rentMovie() throws Exception {
        mockMvc.perform(post("/customerId/9/movieId/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].Title", is("Titanic")))
                .andExpect(jsonPath("$[0].Genre", is("Historical Drama")))
                .andExpect(jsonPath("$[0].Price", is(8.0)));
    }

    @Test
    @DisplayName("GET to retrieve all users rentals")
    void showRentals() throws Exception {
        //Simply adding rental of Transformers to test users account
        //Simply adding rental of Pirates of the Caribbean to test users account
        rentalService.rentMovie(9, 1);
        rentalService.rentMovie(9, 2);
        mockMvc.perform(get("/customerId/9/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].Title", is("Titanic")))
                .andExpect(jsonPath("$[0].Genre", is("Historical Drama")))
                .andExpect(jsonPath("$[0].Price", is(8.0)))
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[1].Title", is("Transformers")))
                .andExpect(jsonPath("$[1].Genre", is("Action")))
                .andExpect(jsonPath("$[1].Price", is(8.0)));
    }

    @Test
    @DisplayName("Testing Controller to retrieve User specified rental")
    void getRental() throws Exception {
        //Renting Pirates of the Caribbean to setup Test
        rentalService.rentMovie(9, 2);
        mockMvc.perform(get("/customerId/9/rentals/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0].Title", is("Transformers")))
                .andExpect(jsonPath("$[0].Genre", is("Action")))
                .andExpect(jsonPath("$[0].Description", is("When Alien robots land on earth. There is only one man who can help.")))
                .andExpect(jsonPath("$[0].Length", is("2 hours 24 minutes")))
                .andExpect(jsonPath("$[0].Price", is(8.0)))
                .andExpect(jsonPath("$[0].['Rent Length']", is(3)));
    }

    @Test
    @DisplayName("DELETE to remove movie from users rentals")
    void removeRentalView() throws Exception {
        //Simply renting Pirates of the Caribbean to set up user test
        //movieId == 2
        rentalService.rentMovie(9, 2);
        mockMvc.perform(delete("/customerId/9/removeRental/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("User Name: test Rental: Transformers has been removed from their rentals"));
    }
}