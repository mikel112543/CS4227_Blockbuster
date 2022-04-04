/*
package com.example.movierental.controller;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.model.Movie;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
     com.example.movierental.service.MovieServiceImpl movieService;

    @Autowired
    UserRepoServiceImpl userRepoService;


    @BeforeEach
    void setUp() {
        Movie movie1 = new Movie.MovieBuilder("The Dark Knight", "Action", "Batman and that", "2 hours", 99, "batmancoverphoto").setPrice(1, userRepoService).build();
        Movie movie2 = new Movie.MovieBuilder("The return of the king", "Action", "Lord of the Rings", "2 hours", 100, "lordoftheringscoverphoto").setPrice(1, userRepoService).build();
    }

    @AfterEach
    void tearDown(){
        movieService.removeMovie();
    }

    @Test
    @DisplayName("Get to retrieve a movie from the movieID")
    void findByMovieID () throws Exception{
        mockMvc.perform(get("/movies/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Title", is("The Dark Knight")))
                .andExpect(jsonPath("$.Genre", is("Action")));
    }

    @Test
    @DisplayName("GET to retrieve a ListOfMovies from the search ")
    void searchMovie () throws Exception{
        mockMvc.perform(get("/movies/search/The")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].Description", is("Batman and that")))
                .andExpect(jsonPath("$[0].Title", is("The Dark Knight")))
                .andExpect(jsonPath("$[1].Title", is ("The return of the king")))
                .andExpect(jsonPath("$[1].Description", is("Lord of the Rings")));
    }

    @Test
    @DisplayName("GET to retrieve a ListOfMovies from the search ")
    void genreSearchMovie () throws Exception {
        mockMvc.perform(get("/movies/genreSearch/Action")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].Description", is("Batman and that")))
                .andExpect(jsonPath("$[0].Title", is("The Dark Knight")))
                .andExpect(jsonPath("$[1].Title", is ("The return of the king")))
                .andExpect(jsonPath("$[1].Description", is("Lord of the Rings")));
    }
}
*/
