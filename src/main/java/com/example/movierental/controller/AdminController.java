package com.example.movierental.controller;

import com.example.movierental.command.UserAuthorization;
import com.example.movierental.command.UserDiscount;
import com.example.movierental.model.User;
import com.example.movierental.service.AdminServiceImpl;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Author - Michael Danaher
 */
@RestController//CRUD
public class AdminController {

    UserRepoServiceImpl userService;
    AdminServiceImpl adminService;
    MovieServiceImpl movieService;
    UserAuthorization userAuthorization;
    UserDiscount userDiscount;

    @Autowired
    public AdminController(UserRepoServiceImpl userService, AdminServiceImpl adminService, MovieServiceImpl movieService, UserAuthorization userAuthorization, UserDiscount userDiscount) {
        this.userService = userService;
        this.adminService = adminService;
        this.movieService = movieService;
        this.userAuthorization = userAuthorization;
        this.userDiscount = userDiscount;
    }

    @GetMapping (value = "/admin")
    public String welcome() {
        return "Welcome Admin";
    }

    /**
     * @param customerId - Unique identifier attached to each of the users.
     * @return String
     */
    @GetMapping (value = "/admin/customerId/{CUSTOMER_ID}/ban")
    public String banCustomer(@PathVariable("CUSTOMER_ID") final int customerId) {
        User user = userService.findByID(customerId);
        adminService.banCustomer(customerId);
        return "User: " + user.getUsername() + " has been banned";
    }

    /**
     * @param customerId - Unique identifier attached to each of the users.
     * @return String
     */
    @GetMapping(value = "/admin/customerId/{CUSTOMER_ID}/unban")
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
    @PostMapping(value = "/admin/movieTitle/{MOVIE_TITLE}/movieGenre/{MOVIE_GENRE}/movieDescription/{MOVIE_DESCRIPTION}/movieLength/{MOVIE_LENGTH}/moviePrice/{MOVIE_PRICE}/movieCoverUrl/{MOVIE_COVER_URL}")
    public String addMovie(@PathVariable("MOVIE_TITLE") final String movieTitle,
                         @PathVariable("MOVIE_GENRE") final String movieGenre,
                         @PathVariable("MOVIE_DESCRIPTION") String movieDescription,
                         @PathVariable("MOVIE_LENGTH") final String movieLength,
                         @PathVariable("MOVIE_PRICE") int moviePriceCode,
                         @PathVariable("MOVIE_COVER_URL") String movieCoverUrl) throws IOException, GeoIp2Exception {

        adminService.addMovie(movieTitle, movieGenre, movieDescription,
                movieLength, moviePriceCode, movieCoverUrl);
        return movieTitle + "has been added to the catalog";
    }

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return boolean - Returns boolean if movie was correctly removed.
     */
    @DeleteMapping(value = "/admin/MovieIdDelete/{movieId}")
    public String deleteMovie(@PathVariable("movieId") final int movieId) {
        String movieName = movieService.findByMovieID(movieId).getTitle();
        adminService.deleteMovie(movieId);
        return movieName + " has been removed from the entire catalog";
    }

    /**
     * @return returns all users
     */
    @GetMapping(value = "/admin/users")
    public List<User> listAllUsers() {
        return adminService.listAllUsers();
    }


    @GetMapping(value = "/admin/userID/{user_ID}/toggleban")
    public String toggleBan(@PathVariable("user_ID") final int userID)  {
        String msg = "";
        String username = userService.findByID(userID).getUsername();
        User user = userService.findByID(userID);
        if (!user.isBanned()) {
            userAuthorization.banUser(userID);
            msg = username + " has been banned";
        } else {
            userAuthorization.unbanUser(userID);
            msg = username + " has been unbanned";
        }
        return msg;
    }

    @GetMapping(value = "/admin/userID/{user_ID}/toggleDiscount")
    public String toggleDiscount(@PathVariable("user_ID") final int userID) {
        String msg = "";
        String username = userService.findByID(userID).getUsername();
        User user = userService.findByID(userID);
        if (user.isDiscount()) {
            userDiscount.removeDiscount(userID);
            msg = username + " has had their discount removed";
        } else {
            userDiscount.addDiscount(userID);
            msg = username + " has been given a discount";
        }
        return msg;
    }
}
