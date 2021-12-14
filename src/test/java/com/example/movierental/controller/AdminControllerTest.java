package com.example.movierental.controller;

import com.example.movierental.model.Movie;
import com.example.movierental.model.User;
import com.example.movierental.service.AdminServiceImpl;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.RentalServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import org.junit.jupiter.api.AfterEach;
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
        User testUserUnbanned = new User(99, "test", "test", "ROLE_USER", false);
        User testUserBanned = new User(99, "test", "test", "ROLE_USER", true);

        User testAdmin = new User(100, "admin", "admin", "ROLE_ADMIN", false);
        Movie movie1 = new Movie.MovieBuilder("The Dark Knight", "Action", "Batman and that", "2 hours", 99, "batmancoverphoto").setPrice(1).build();
        Movie movie2 = new Movie.MovieBuilder("The return of the king", "Action", "Lord of the Rings", "2 hours", 100, "lordoftheringscoverphoto").setPrice(1).build();
        movieService.addMovie(movie1);
        movieService.addMovie(movie2);
        userService.addUser(testUserUnbanned);
        userService.addUser(testUserBanned);
    }

    @AfterEach
    void tearDown(){
        movieService.removeMovie();
    }

    @Test
    @DisplayName("POST to add a movie to the List of Movies")
    void addMovie() throws Exception {
        mockMvc.perform(post("/admin/movieTitle/Transformers/movieGenre/Action/movieDescription/Robots and Stuff/movieLength/2 hours/moviePrice/1/movieCoverUrl/transformers.jpg")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Movie.Title", is("Transformers")))
                .andExpect(jsonPath("$.Movie.Genre", is("Action")))
                .andExpect(jsonPath("$.Movie.Description", is("Robots and Stuff")))
                .andExpect(jsonPath("$.Movie.Length", is("2 hours")));

    }


}
