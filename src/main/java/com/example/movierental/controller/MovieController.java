package com.example.movierental.controller;

import com.example.movierental.model.Movie;
import com.example.movierental.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController //CRUD
public class MovieController {

    @Autowired
    MovieService movieService;

    /**
     * @return List of Movies
     */
/*    @GetMapping(value = "/movies")
    public Movie listAll() {
        return movieService.listAllMovies();
    }*/

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return JSON Movie
     */
    @GetMapping(value = "movies/{MOVIE_ID}")
    public Movie getMovie(@PathVariable("MOVIE_ID") final int movieId) {
        return movieService.findByMovieID(movieId); // Send request to movie service handler
    }

    /**
     *
     * @param movieName - name of movie to be searched
     * @return JSON Movie
     */
/*    @GetMapping(value = "/movies/search/{MOVIE_NAME}")
    public Movie searchMovie(@PathVariable("MOVIE_NAME") String movieName) {
        return movieService.searchMovie(movieName);
    }*/
}
