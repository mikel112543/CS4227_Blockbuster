package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.Dispatcher;
import com.example.movierental.logger.LoggerInterceptor;
import com.example.movierental.model.Movie;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepoServiceImpl userService;
    private final MovieServiceImpl movieService;
    private final Dispatcher dispatcher;
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    public AdminServiceImpl(UserRepoServiceImpl userService, MovieServiceImpl movieService, Dispatcher dispatcher) {
        this.userService = userService;
        this.movieService = movieService;
        this.dispatcher = dispatcher;
    }

    @Override
    public void addMovie(String title, String genre, String description, String length, int priceCode, String movieCoverUrl){
        List<Movie> listOfMovies = movieService.getMovies();
        int movieId = listOfMovies.get(listOfMovies.size()-1).getMovieId()+1;

        Movie movie = new Movie.MovieBuilder(title, genre, description, length, movieId, movieCoverUrl).setPrice(priceCode, userService).build();
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
        dispatcher.logMessage(log, "Incorrect Movie ID", LoggerInterceptor.ERROR);
        throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
    }

    @Override
    public List<User> listAllUsers() {
        return userService.getUsers();
    }

    @Override
    public void banCustomer(int userID) {
        List<User> listOfUsers = userService.getUsers();
        User user = userService.findByID(userID);
        for (int i = 0 ; i < listOfUsers.size() ; i++){
            if (userID == user.getUserID()){
                user.setBanned(true);
            }
        }
        dispatcher.logMessage(log, "Could not find user", LoggerInterceptor.ERROR);
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

    @Override
    public void unbanCustomer(int userID){
        List<User> listOfUsers = userService.getUsers();
        User user = userService.findByID(userID);
        for (int i = 0 ; i < listOfUsers.size() ; i++){
            if (userID == user.getUserID()){
                user.setBanned(false);
            }
        }
        dispatcher.logMessage(log, "Could not find user", LoggerInterceptor.ERROR);
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

}