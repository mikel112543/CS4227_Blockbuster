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

    @Autowired
    UserServiceImpl userService;


    @Override
    public void addMovie(String title, String genre, String description, double length, int priceCode, int movieRating){
        List<Movie> listOfMovies = movieService.getMovies();
        int movieId = listOfMovies.get(listOfMovies.size()-1).getMovieId()+1;
        Movie movie = new Movie(movieId, title, genre, description, length, priceCode, movieRating);
        //TODO: BUILDER METHOD GOES IN HERE
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
    public void banCustomer(int userID) {
        List<User> listOfUsers = userService.getUsers();
        User user = userService.findById(userID);
        for (int i = 0 ; i < listOfUsers.size() ; i++){
            if (userID == user.getUserID()){
                user.setAccountNonLocked(false);
            }
        }
    }
}