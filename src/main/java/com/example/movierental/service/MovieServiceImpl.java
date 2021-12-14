package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Movie;
import com.example.movierental.model.ServiceError;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {
    ArrayList<Movie> listOfMovies = new ArrayList<>();
    private static AbstractLogger chainLogger = RequesterClient.getChaining();


    public ArrayList<Movie> getMovies() {
        return listOfMovies;
    }

    /**
     *
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
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find movie");
        throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_ID));
    }

    /**
     *
     * @param searchbar - User searches X
     * @return ArrayList of Movies if their title contains X in the title
     */
    @Override
    public ArrayList<Movie> findByName(String searchbar){
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie listOfMovie : listOfMovies) {
            if (listOfMovie.getTitle().toLowerCase().contains(searchbar.toLowerCase())) {
                results.add(listOfMovie);
            }
        }
        if (results.isEmpty()){
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "No movies available");
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_NAME));
        }
        return results;
    }

    /**
     *
     * @param insertedGenre - the genre a user searches for
     * @return ArrayList of Movies if their genre contains the searched genre
     */

    @Override
    public ArrayList<Movie> findByGenre(String insertedGenre){
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : listOfMovies){
            if (movie.getGenre().toLowerCase().contains(insertedGenre.toLowerCase())){
                results.add(movie);
            }
        }
        if (results.isEmpty()){
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "No movies available");
            throw new ServiceException(new ServiceError(Error.INVALID_MOVIE_NAME));
        }
        return results;
    }

    /**
     * Initializes list of movies from movies csv
     */
    @Override
    public void initializeMovies() {
        String path = "Movies.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                                                    //title     genre      description length   movieID                       movieCover
                Movie movie = new Movie.MovieBuilder(values[0], values[1], values[2], values[3], Integer.parseInt(values[5]), values[6]).setPrice(Integer.parseInt(values[4])).build();
                listOfMovies.add(movie);
                System.out.println(movie.getPrice().getCharge());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
