package com.example.movierental.service;


import com.example.movierental.model.Movie;

import java.io.IOException;
import java.util.ArrayList;

public interface MovieService {
    ArrayList<Movie> listAllMovies() throws IOException;

    Movie createMovie(String[] metadata);

    Movie findByMovieID(int movieID);

    void InitializeList();

    ArrayList<Movie> findByName(String searchbar) throws IOException;
}