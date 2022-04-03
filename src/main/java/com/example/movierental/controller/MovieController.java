package com.example.movierental.controller;

import com.example.movierental.memento.LoyaltyPointsMemento;
import com.example.movierental.memento.LoyaltyPointsTracker;
import com.example.movierental.model.Movie;
import com.example.movierental.model.User;
import com.example.movierental.service.MovieService;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.UserRepoService;
import com.example.movierental.service.UserRepoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author - Michael Danaher
 */
@Controller //CRUD
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;
    UserRepoServiceImpl userRepoService;

    /**
     * @param movieModel - List of movies to be attached to model
     * @return - movies.html
     */
    @GetMapping(value = "/movies")
    public String showMovies(Model movieModel, Model pointsModel) throws IOException,  ClassNotFoundException {
        movieService.clearMovies();
        movieService.initializeMovies();
        movieModel.addAttribute("movies", movieService.getMovies());
        pointsModel.addAttribute("points", movieService.getPossiblePoints());
        return "movies";
    }

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return JSON Movie
     */
    @GetMapping(value = "movies/{MOVIE_ID}")
    @ResponseBody
    public Movie getMovie(@PathVariable("MOVIE_ID") final int movieId, HttpServletRequest request, HttpServletResponse response) {
        String test = request.getRequestURI();
        int status = response.getStatus();
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