package com.example.movierental.service;


import com.example.movierental.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MovieService {
    ArrayList<Movie> listAllMovies();

    Movie findByMovieID(int movieID);

    ArrayList<Movie> findByName(String searchbar);

    void initializeListOfMovies();
}