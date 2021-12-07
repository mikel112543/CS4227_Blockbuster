package com.example.movierental.service;


import com.example.movierental.model.Movie;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public abstract class AdminServiceImpl implements AdminService {
    ArrayList<User> ListOfUsers = new ArrayList<User>();
    ArrayList<Movie> ListOfMovies = new ArrayList<Movie>();

    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    UserServiceImpl userService;


    @Override
    public void addMovie(String title, String genre, String description, String length, int price, int movieID)  {
        Movie movie = new Movie.MovieBuilder(title, genre, description, length, movieID).setPrice(price).build();
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
       User user = userService.findById(userID);
        for (int i = 0 ; i < ListOfUsers.size() ; i++){
            if (userID == user.getUserID()){
                user.setAccountNonLocked(false);
            }
        }
    }
}