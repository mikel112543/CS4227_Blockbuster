package com.example.movierental.service;

import com.example.movierental.abstractFactory.*;
import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.Dispatcher;
import com.example.movierental.logger.LoggerInterceptor;
import com.example.movierental.memento.LoyaltyPointsMemento;
import com.example.movierental.memento.LoyaltyPointsTracker;

import com.example.movierental.model.*;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    ArrayList<Movie> listOfMovies = new ArrayList<>();
    private Map<String, Integer> moviePoints = new HashMap<String, Integer>();
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    UserRepoServiceImpl userRepoService;
    UserLocationServiceImpl userLocationService = new UserLocationServiceImpl();
    Dispatcher dispatcher;

    @Autowired
    public MovieServiceImpl(UserRepoServiceImpl userRepoService, Dispatcher dispatcher) throws IOException, GeoIp2Exception {
        this.userRepoService = userRepoService;
        this.dispatcher = dispatcher;
    }

    public ArrayList<Movie> getMovies() {
        return listOfMovies;
    }

    /**
     * @param movieID - ID of the movie
     * @return Object of the movie if its in the list
     */
    @Override
    public Movie findByMovieID(int movieID) {
        for (Movie movie : listOfMovies) {
            if (movie.getMovieId() == movieID) {
                return movie;
            }
        }
        dispatcher.logMessage(log, "Could not find movie", LoggerInterceptor.ERROR);
        throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
    }

    /**
     * @param searchbar - User searches X
     * @return ArrayList of Movies if their title contains X in the title
     */
    @Override
    public ArrayList<Movie> findByName(String searchbar) {
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie listOfMovie : listOfMovies) {
            if (listOfMovie.getTitle().toLowerCase().contains(searchbar.toLowerCase())) {
                results.add(listOfMovie);
            }
        }
        if (results.isEmpty()) {
            dispatcher.logMessage(log, "No movies available", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_NAME));
        }
        return results;
    }

    /**
     * @param insertedGenre - the genre a user searches for
     * @return ArrayList of Movies if their genre contains the searched genre
     */

    @Override
    public ArrayList<Movie> findByGenre(String insertedGenre) {
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : listOfMovies) {
            if (movie.getGenre().toLowerCase().contains(insertedGenre.toLowerCase())) {
                results.add(movie);
            }
        }
        if (results.isEmpty()) {
            dispatcher.logMessage(log, "No movies available", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_NAME));
        }
        return results;

    }

    /**
     * Initializes list of movies from movies csv
     */
    @Override
    public void initializeMovies() throws IOException, GeoIp2Exception {
        String path = getMoviePath();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                //title     genre      description length   movieID                                                  Price Code
                Movie movie = new Movie.MovieBuilder(values[0], values[1], values[2], values[3], Integer.parseInt(values[5]), values[6]).setPrice(Integer.parseInt(values[4]), userRepoService).build();
                listOfMovies.add(movie);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return;
        }

        LoyaltyPointsTracker pointsTracker = new LoyaltyPointsTracker(userRepoService);
        LoyaltyPointsMemento pointsMemento = new LoyaltyPointsMemento(pointsTracker);

        for (Movie movie : listOfMovies) {
            pointsTracker.setLoyaltyPoints(movie.getLoyaltyPoints());
            moviePoints.put(movie.getTitle(), pointsTracker.getLoyaltyPoints());
            pointsMemento.restoreState();
        }
    }

    @Override
    public Map<String, Integer> getPossiblePoints() {
        return moviePoints;
    }

    /**
     * Empties the current list of movies
     */
    @Override
    public void clearMovies() {
        listOfMovies.clear();
        moviePoints.clear();
    }

    @Override
    public String getMoviePath() {
        String path;
        assert false;
        String userLocation = userLocationService.getLocation();;

        AbstractMovieRegionFactory factory = null;
        switch (userLocation) {
            case "United States":
                factory = new moviesAvailableInAmericaFactory();
                break;
            case "United Kingdom":
                factory = new moviesAvailableInBritainFactory();
                break;
            case "Ireland":
                factory = new moviesAvailableInIrelandFactory();
                break;
        }
        moviesAvailable movies = factory.createMovie();
        path = movies.getMovieLists();
        return path;
    }
}
