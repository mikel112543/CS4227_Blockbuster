package com.example.movierental.controller;

import com.example.movierental.model.Movie;
import com.example.movierental.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author - Michael Danaher
 */
@Controller //CRUD
public class MovieController {

    @Autowired
    MovieService movieService;

    /**
     * @param model - List of movies to be attached to model
     * @return - movies.html
     */
    @GetMapping(value = "/movies")
    public String showMovies(Model model) {
        movieService.initializeMovies();
        model.addAttribute("movies", movieService.getMovies());
        return "movies";
    }

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return JSON Movie
     */
    @GetMapping(value = "movies/{MOVIE_ID}")
    @ResponseBody
    public Movie getMovie(@PathVariable("MOVIE_ID") final int movieId) {
        return movieService.findByMovieID(movieId); // Send request to movie service handler
    }

    /**
     * @param movieName - name of movie to be searched
     * @return JSON List of movies
     */
    @GetMapping(value = "/movies/search/{MOVIE_NAME}")
    @ResponseBody
    public List<Movie> searchMovie(@PathVariable("MOVIE_NAME") String movieName) {
        return movieService.findByName(movieName);
    }

    /**
     * @param genreName - name of genre to be searched
     * @return JSON list of movies that the genre.
     */
    @GetMapping(value = "/movies/genreSearch/{GENRE_NAME}")
    @ResponseBody
    public List<Movie> genreSearchMovie(@PathVariable("GENRE_NAME") String genreName){
        return movieService.findByGenre(genreName);
    }
}