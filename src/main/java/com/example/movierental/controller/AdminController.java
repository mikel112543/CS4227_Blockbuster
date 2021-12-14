package com.example.movierental.controller;

import com.example.movierental.model.User;
import com.example.movierental.service.AdminServiceImpl;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author - Michael Danaher
 */
@RestController //CRUD
public class AdminController {

    @Autowired
    UserRepoServiceImpl userService;

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    MovieServiceImpl movieService;

    /**
     * @param customerId - Unique identifier attached to each of the users.
     * @return String
     */
    @PostMapping(value = "admin/customerId/{CUSTOMER_ID}/ban")
    public String banCustomer(@PathVariable("CUSTOMER_ID") final int customerId) {
        User user = userService.findByID(customerId);
        adminService.banCustomer(customerId);
        return "User: " + user.getUsername() + " has been banned";
    }


    /**
     * @param customerId - Unique identifier attached to each of the users.
     * @return String
     */
    @PostMapping(value = "admin/customerId/{CUSTOMER_ID}/unban")
    public String unbanCustomer(@PathVariable("CUSTOMER_ID") final int customerId) {
        User user = userService.findByID(customerId);
        adminService.unbanCustomer(customerId);
        return "User: " + user.getUsername() + " has been unbanned";
    }

    /**
     * @param movieTitle       - Title of the movie to be added
     * @param movieGenre       - Genre of the movie to be added
     * @param movieDescription - Short description of the movie to the added
     * @param movieLength      - Length of movie to be added
     * @param moviePriceCode       - Starting price of movie to be added
     * @return - Json response
     */
    @PostMapping(value = "admin/movieTitle/{MOVIE_TITLE}/movieGenre/{MOVIE_GENRE}/movieDescription/{MOVIE_DESCRIPTION}/movieLength/{MOVIE_LENGTH}/moviePrice/{MOVIE_PRICE}/movieCoverUrl/{MOVIE_COVER_URL}")
    public String addMovie(@PathVariable("MOVIE_TITLE") final String movieTitle,
                         @PathVariable("MOVIE_GENRE") final String movieGenre,
                         @PathVariable("MOVIE_DESCRIPTION") String movieDescription,
                         @PathVariable("MOVIE_LENGTH") final String movieLength,
                         @PathVariable("MOVIE_PRICE") int moviePriceCode,
                         @PathVariable("MOVIE_COVER_URL") String movieCoverUrl) {

        adminService.addMovie(movieTitle, movieGenre, movieDescription,
                movieLength, moviePriceCode, movieCoverUrl);
        return movieTitle + "has been added to the catalog";
    }

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
