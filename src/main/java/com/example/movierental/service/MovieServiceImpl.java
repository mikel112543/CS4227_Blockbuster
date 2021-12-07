package com.example.movierental.service;

import com.example.movierental.model.Movie;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {
    // this gets autowired in I believe
    ArrayList<Movie> ListOfMovies = new ArrayList<Movie>();

    public ArrayList<Movie> listAllMovies() {
        return ListOfMovies;
    }

    @Override
    public Movie findByMovieID(int movieID) {
        Movie movie = new Movie();
        for (int i = 0; i < ListOfMovies.size(); i++) {
            if (ListOfMovies.get(i).getMovieId() == movieID) {
                movie = ListOfMovies.get(i);
            }
        }
        return movie;
    }

    @Override
    public ArrayList<Movie> findByName(String searchbar) {
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (int i = 0; i < ListOfMovies.size(); i++) {
            if (searchbar.contains(ListOfMovies.get(i).getTitle())) {
                results.add(ListOfMovies.get(i));
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
                ListOfMovies.add(movie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}