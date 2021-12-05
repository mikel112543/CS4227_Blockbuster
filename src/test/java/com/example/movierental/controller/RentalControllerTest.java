package com.example.movierental.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rentMovie() throws Exception {
        this.mockMvc.perform(post("/customerId/1/movieId/2"))
                .andExpect(status().isOk());
    }

    @Test
    void showRentals() throws Exception {
        this.mockMvc.perform(get("/customerId/1/rentals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("customerId", is(1)));
    }

    @Test
    void getRental() {
    }

    @Test
    void removeRentalView() {
    }
}