package com.example.movierental.service;


import com.example.movierental.model.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MovieService {

    void initializeList();

    List<Movie> getMovies();

    Movie createMovie(String[] metadata);

    Movie findByMovieID(int movieID);

    ArrayList<Movie> findByName(String searchbar);
}