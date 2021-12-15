package com.example.movierental.service;


import com.example.movierental.model.Movie;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.util.ArrayList;


public interface MovieService {
    ArrayList<Movie> getMovies();

    Movie findByMovieID(int movieID);

    ArrayList<Movie> findByName(String searchbar);

    void initializeMovies();
}