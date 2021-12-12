package com.example.movierental.controller;

import com.example.movierental.model.User;
import com.example.movierental.service.AdminServiceImpl;
import com.example.movierental.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author - Michael Danaher
 */
@RestController //CRUD
public class AdminController {

    AdminServiceImpl adminService;
    MovieServiceImpl movieService;

    @Autowired
    public AdminController(AdminServiceImpl adminService, MovieServiceImpl movieService) {
        this.adminService = adminService;
        this.movieService = movieService;
    }
/*
    *//**
     * @param userId - Unique identifier attached to each of the users.
     * @return User - Returns object of type user.
     *//*
    @PostMapping(value = "admin/customerId/{CUSTOMER_ID}")
    public String banCustomer(@PathVariable("CUSTOMER_ID") final int customerId) {
        return "User"
    }*/

    /**
     * @param movieTitle       - Title of the movie to be added
     * @param movieGenre       - Genre of the movie to be added
     * @param movieDescription - Short description of the movie to the added
     * @param movieLength      - Length of movie to be added
     * @param moviePrice       - Starting price of movie to be added
     * @return - Json response
 /*    *//*
    @PostMapping(value = "admin/movieTitle/{MOVIE_TITLE}/movieGenre/{MOVIE_GENRE}/movieDescription/{MOVIE_DESCRIPTION}/movieLength/{MOVIE_LENGTH}/moviePrice/{MOVIE_PRICE}")
    public User addMovie(@PathVariable("MOVIE_TITLE") final String movieTitle,
                          @PathVariable("MOVIE_GENRE") final String movieGenre,
                          @PathVariable("MOVIE_DESCRIPTION") String movieDescription,
                          @PathVariable("MOVIE_LENGTH") final double movieLength,
                          @PathVariable("MOVIE_PRICE") double moviePrice) {
        return adminService.addMovie(movieTitle, movieGenre, movieDescription,
                movieLength, moviePrice);
    }*/

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return boolean - Returns boolean if movie was correctly removed.
     */
    @DeleteMapping(value = "admin/MovieIdDelete/{movieId}")
    public String deleteMovie(@PathVariable("movieId") final int movieId) {
        String movieName = movieService.findByMovieID(movieId).getTitle();
        adminService.deleteMovie(movieId);
        return movieName + " has been removed from the entire catalog";
    }

    /**
     * @return returns all users
     */
    @GetMapping(value = "admin/users")
    public List<User> listAllUsers() {
        return adminService.listAllUsers();
    }
}