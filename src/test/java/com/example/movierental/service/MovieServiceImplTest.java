package com.example.movierental.service;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Movie Service Tests")
class MovieServiceImplTest {

    UserRepoServiceImpl userRepoService;
    RentalServiceImpl rentalService;
    MovieServiceImpl movieService;

    @Autowired
    public MovieServiceImplTest(UserRepoServiceImpl userRepoService, RentalServiceImpl rentalService, MovieServiceImpl movieService) {
        this.userRepoService = userRepoService;
        this.rentalService = rentalService;
        this.movieService = movieService;
    }

    @BeforeEach
    void setUp() {
        movieService.initializeMovies();
    }

    @AfterTestExecution
    public void tearDown() {
        movieService.getMovies().clear();
    }

    @Test
    @DisplayName("Should return movie by Id")
    void findByMovieId() {
        Movie movie = movieService.findByMovieID(1);
        assertEquals("Titanic", movie.getTitle());
        assertEquals("Historical Drama", movie.getGenre());
        assertEquals("Doomed before departure. Titanic explores the relationship between a woman of high class and a commoner before the infamous tragedy.", movie.getDescription());
        assertEquals("2 hours", movie.getLength());
    }

    @Test
    @DisplayName("Should return a movie by title")
    void findByMovieTitle() {
        ArrayList<Movie> movies = movieService.findByName("titanic");
        assertEquals("Titanic", movies.get(0).getTitle());
        assertEquals("Historical Drama", movies.get(0).getGenre());
        assertEquals("Doomed before departure. Titanic explores the relationship between a woman of high class and a commoner before the infamous tragedy.", movies.get(0).getDescription());
        assertEquals("2 hours", movies.get(0).getLength());
    }

    @Test
    @DisplayName("Should return a movie by genre")
    void findMovieByGenre() {
        ArrayList<Movie> movies = movieService.findByGenre("Family");
        assertEquals("Peter Pan", movies.get(0).getTitle());
        assertEquals("Family", movies.get(0).getGenre());
        assertEquals("A mysterious flying boy takes some adventurous kids to see the island of Neverland.", movies.get(0).getDescription());
        assertEquals("Luca", movies.get(1).getTitle());
        assertEquals("Family", movies.get(1).getGenre());
        assertEquals(" Luca lives underwater until his friend suggests they go ashore. ", movies.get(1).getDescription());
    }

    @Test
    @DisplayName("Should return ServiceError for invalid movie ID")
    void invalidMovieId() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            Movie movie = movieService.findByMovieID(99);
        });
        assertEquals("2003", exception.getServiceError().getErrorCode());
        assertEquals("Invalid movie ID", exception.getServiceError().getErrorMessage());
    }

    @Test
    @DisplayName("Should return ServiceError for invalid movie title")
    void invalidMovieName() {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            ArrayList<Movie> movies = movieService.findByName("King Kong");
        });
        assertEquals("2010", exception.getServiceError().getErrorCode());
        assertEquals("Can't find movie with search, search for another movie instead", exception.getServiceError().getErrorMessage());
    }

}
