package com.example.movierental.service;

import com.example.movierental.model.Movie;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MovieServiceImpl movieService;

    @Override
    public void addMovie(int movieID, String title, String genre, String description, String length, int price, int movieRating){
        Movie movie = new Movie(movieID, title, genre, description, length, price, movieRating);
        //TODO: BUILDER METHOD GOES IN HERE
        List<Movie> listOfMovies = movieService.getMovies();
        listOfMovies.add(movie);
    }

    @Override
    public void deleteMovie(int movieID) {
        List<Movie> listOfMovies = movieService.getMovies();
       Movie movie = movieService.findByMovieID(movieID);
       for(int i = 0 ; i < listOfMovies.size() ; i++){
            if (movieID == movie.getMovieId()){
                listOfMovies.remove(movie);
            }
       }
    }
    @Override
    public List<User> listAllUsers() {
        return userService.getUsers();
    }

    @Override
    public User createCustomer(String[] metadata) {
        return null;
    }

    @Override
    public void banCustomer(int userID) {
        List<User> listOfUsers = userService.getUsers();
        for (User listOfUser : listOfUsers) {
            if (listOfUser.getUserID() == userID) {
                listOfUser.setBanned(true);
                return;
            }
        }
    }
}