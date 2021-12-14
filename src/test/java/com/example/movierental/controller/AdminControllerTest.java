package com.example.movierental.controller;

import com.example.movierental.model.User;
import com.example.movierental.service.AdminServiceImpl;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.RentalServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;




import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepoServiceImpl userService;

    @Autowired
    RentalServiceImpl rentalService;

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    MovieServiceImpl movieService;


    @BeforeEach
    void setUp()  {
        User testUser = new User(99, "test", "test", "ROLE_USER", false);
        User testAdmin = new User(100, "admin", "admin", "ROLE_ADMIN", false);

    }

    @Test
    @DisplayName("POST to add a movie to the List of Movies")
    void addMovie() throws Exception {
        mockMvc.perform(post("/admin/movieTitle/Transformers/movieGenre/action/movieDescription/robots and stuff/movieLength/2 hours/moviePrice/1/movieCoverUrl/transformers.jpg")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Movie").exists())
                .andExpect(jsonPath("$[0].Movie.Title", is("Transformers")))
                .andExpect(jsonPath("$[0].Movie.Genre", is("Action")));
    }


}
