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

    @Override
    public void initializeMovies() {
        String path = "Movies.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                                                    //title     genre      description length   movieID                                                  Price Code
                Movie movie = new Movie.MovieBuilder(values[0], values[1], values[2], values[3], Integer.parseInt(values[5])).setPrice(Integer.parseInt(values[4])).build();
                movie.setMovieCoverUrl(values[6]);
                listOfMovies.add(movie);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
