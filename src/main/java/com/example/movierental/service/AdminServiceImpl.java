package com.example.movierental.service;

import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserServiceImpl userService;

    ArrayList<User> listOfUsers = new ArrayList<User>();
    ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
    @Override
    public void addMovie(String title, String genre, String description, Duration length, int price, int movieID, int movieRating) throws IOException {
        Movie movie = new Movie(title, genre, description, length, price, movieID, movieRating);
        //TODO: BUILDER METHOD GOES IN HERE
        ListOfMovies.add(movie);
    }

    @Override
    public void deleteMovie(int movieID) {
       Movie movie = movieService.findByMovieID(movieID);
       for (int i = 0 ; i < ListOfMovies.size() ; i++){
            if (movieID == movie.getMovieId()){
                ListOfMovies.remove(movie);
            }
       }
    }
    @Override
    public ArrayList<User> listAllUsers() {
        return ListOfUsers;
    }

    @Override
    public void banCustomer(int userID) {
       User user = userService.findbyId(userID);
        for (int i = 0 ; i < ListOfUsers.size() ; i++){
            if (userID == user.getUserID()){
                user.setBanned(true);
            }
        }
    }
}