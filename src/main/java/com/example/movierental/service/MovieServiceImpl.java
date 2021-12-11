package com.example.movierental.service;

import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Movie;
import com.example.movierental.model.ServiceError;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    ArrayList<Movie> listOfMovies = new ArrayList<>();
    private static AbstractLogger chainLogger = RequesterClient.getChaining();


    public ArrayList<Movie> listAllMovies() {
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
        return results;
    }

    @Override
    public void initializeListOfMovies() {
        String path = "movies.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((br.readLine() != null)) {
                String[] values = line.split(",");
                /*Movie movie = new Movie();
                movie.setTitle(values[0]);
                movie.setGenre(values[1]);
                movie.setDescription(values[2]);
                movie.setLength(values[3]);
                movie.setPrice(Integer.parseInt(values[4]));
                movie.setMovieId(Integer.parseInt(values[5]));
                 */
                Movie movie = new Movie.MovieBuilder(values[0], values[1], values[2], values[3], Integer.parseInt(values[5])).setPrice(Integer.parseInt(values[4])).build();
                listOfMovies.add(movie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
