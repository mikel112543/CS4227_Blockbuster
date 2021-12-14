package com.example.movierental.controller;

import com.example.movierental.model.Movie;
import com.example.movierental.service.MovieServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MovieServiceImpl movieService;


    @BeforeEach
    void setUp() {
        List<Movie> testMovies = new ArrayList<>();
        Movie movie1 = new Movie.MovieBuilder("The Dark Knight", "Action", "Batman and that", "2 hours", 1, "batmancoverphoto").setPrice(1).build();
        Movie movie2 = new Movie.MovieBuilder("The return of the king", "Action", "Lord of the Rings", "2 hours", 2, "lordoftheringscoverphoto").setPrice(1).build();
    }

    @Test
    @DisplayName("Get to retrieve a movie from the movieID")
    void findByMovieID () throws Exception{
        mockMvc.perform(get("movies/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Movie").exists())
                .andExpect(jsonPath("$[0].Movie.Title", is("The Dark Knight")))
                .andExpect(jsonPath("$[0].Movie.Genre", is("Action")));

    }
}
