package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Movie;
import com.example.movierental.model.ServiceError;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    ArrayList<Movie> listOfMovies = new ArrayList<>();

    private static AbstractLogger chainLogger = RequesterClient.getChaining();

    @Override
    @PostConstruct
    public void InitializeList() {
        Movie transformers = new Movie(1,"Transformers", "Action", "The movie", "1hr 54mins", 1, 3);
        Movie piratesOfTheCaribbean = new Movie(2,"Pirates of the Caribbean", "Action", "pirates", "1hr 38mins", 1, 4);
        Movie titanic = new Movie(3,"The Titanic", "Romance", "It has boats", "2hr 23mins", 1, 4);
        Movie peterPan = new Movie(4,"Peter Pan", "Animation", "It's fun", "1hr 20mins", 2, 3);
        Movie luca = new Movie(5,"Luca", "Animation", "Another kids movie", "1hr 28mins", 1, 3);

        listOfMovies.add(transformers);
        listOfMovies.add(piratesOfTheCaribbean);
        listOfMovies.add(titanic);
        listOfMovies.add(peterPan);
        listOfMovies.add(luca);
    }

    public List<Movie> getMovies() {
        return listOfMovies;
    }
    //method used to create the movie object when given an array of it's attributes.
    public Movie createMovie(String [] metadata){
        String title = metadata[0];
        String genre = metadata[1];
        String description = metadata[2];
        String length = metadata[3];
        int price = Integer.parseInt(metadata[4]);
        int movieId = Integer.parseInt(metadata[5]);
        int movieRating = Integer.parseInt(metadata[6]);

        return new Movie (movieId, title, genre, description, length, price, movieRating);
    }

    @Override
    public Movie findByMovieID(int movieID) {
        for (Movie movie : listOfMovies) {
            if (movie.getMovieId() == movieID) {
                return movie;
            }
        }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find movie");
        throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
    }

    @Override
    public ArrayList<Movie> findByName(String searchbar){
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie listOfMovie : listOfMovies) {
            if (searchbar.contains(listOfMovie.getTitle())) {
                results.add(listOfMovie);
            }
        }
        return results;
    }
}