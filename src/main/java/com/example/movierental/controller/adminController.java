/*
package com.example.movierental.controller;

import org.springframework.web.bind.annotation.*;

@RestController //CRUD
public class adminController {

    */
/**
     * @param userId - Unique identifier attached to each of the users.
     * @return User - Returns object of type user.
     *//*

    @DeleteMapping(value = "admin/customerId/{CUSTOMER_ID}")
    public User banCustomer(@PathVariable("USER_ID") final int customerId) {
        return adminService.deleteUser(customerId);
    }

    */
/**
     * @param movieTitle       - Title of the movie to be added
     * @param movieGenre       - Genre of the movie to be added
     * @param movieDescription - Short description of the movie to the added
     * @param movieLength      - Length of movie to be added
     * @param moviePrice       - Starting price of movie to be added
     * @return - Json response
     *//*

    @PostMapping(value = "admin/movieTitle/{MOVIE_TITLE}/movieGenre/{MOVIE_GENRE}/movieDescription/{MOVIE_DESCRIPTION}/movieLength/{MOVIE_LENGTH}/moviePrice/{MOVIE_PRICE}")
    public User addMovie(@PathVariable("MOVIE_TITLE") final String movieTitle,
                          @PathVariable("MOVIE_GENRE") final String movieGenre,
                          @PathVariable("MOVIE_DESCRIPTION") String movieDescription,
                          @PathVariable("MOVIE_LENGTH") final double movieLength,
                          @PathVariable("MOVIE_PRICE") double moviePrice) {
        return adminService.addMovie(movieTitle, movieGenre, movieDescription,
                movieLength, moviePrice);
    }

    */
/**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return boolean - Returns boolean if movie was correctly removed.
     *//*

    @DeleteMapping(value = "admin/MovieIdDelete/{movieId}")
    public User deleteMovie(@PathVariable("movieId") final int movieId) {
        return adminService.deleteMovie(movieId);
    }

    */
/**
     * @return returns all users
     *//*

    @GetMapping(value = "admin/users")
    public User listAllUsers() {
        return adminService.listAllUsers();
    }
}
*/
