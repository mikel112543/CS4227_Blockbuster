package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Movie;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private static AbstractLogger chainLogger = RequesterClient.getChaining();

    private final UserRepoServiceImpl userService;
    private final MovieServiceImpl movieService;

    @Autowired
    public AdminServiceImpl(UserRepoServiceImpl userService, MovieServiceImpl movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @Override
    public void addMovie(String title, String genre, String description, String length, int priceCode, String movieCoverUrl){
        List<Movie> listOfMovies = movieService.getMovies();
        int movieId = listOfMovies.get(listOfMovies.size()-1).getMovieId()+1;
        Movie movie = new Movie.MovieBuilder(title, genre, description, length, movieId, movieCoverUrl).setPrice(priceCode).build();
        listOfMovies.add(movie);


    }

    @Override
    public void deleteMovie(int movieID) {
        List<Movie> listOfMovies = movieService.getMovies();
        Movie movie = movieService.findByMovieID(movieID);
        for(int i = 0 ; i < listOfMovies.size() ; i++){
            if (movieID == listOfMovies.get(i).getMovieId()){
                listOfMovies.remove(movie);
            }
        }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find movie");
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
            if (userID == listOfUsers.get(i).getUserID()){
                user.setBanned(true);
            }
        }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find user");
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

    @Override
    public void unbanCustomer(int userID){
        List<User> listOfUsers = userService.getUsers();
        User user = userService.findByID(userID);
        for (int i = 0 ; i < listOfUsers.size() ; i++){
            if (userID == listOfUsers.get(i).getUserID()){
                user.setBanned(false);
            }
        }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find user");
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

}