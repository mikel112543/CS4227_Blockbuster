package com.example.movierental.service;

import com.example.movierental.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {
    ArrayList<Movie> ListOfMovies = new ArrayList<Movie>();

    public ArrayList<Movie> listAllMovies() {
        return ListOfMovies;
    }

    @Override
    public Movie findByMovieID(int movieID) {
        Movie movie = new Movie();
        for (int i = 0 ; i < ListOfMovies.size() ; i++ ) {
            if (ListOfMovies.get(i).getMovieId() == movieID) {
                movie = ListOfMovies.get(i);
            }
        }
        return movie;
    }
    @Override
    public ArrayList<Movie> findByName(String searchbar){
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (int i = 0 ; i < ListOfMovies.size() ; i++){
            if (searchbar.contains(ListOfMovies.get(i).getTitle())){
                results.add(ListOfMovies.get(i));
            }
        }
        return results;
    }
}
