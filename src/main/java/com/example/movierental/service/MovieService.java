package com.example.movierental.service;


import com.example.movierental.model.Movie;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public interface MovieService {
    ArrayList<Movie> getMovies();

    Movie findByMovieID(int movieID);

    ArrayList<Movie> findByName(String searchbar);

    void initializeMovies() throws IOException, ClassNotFoundException;

    Map<String, Integer> getPossiblePoints();

    public void clearMovies();

    ArrayList<Movie> findByGenre(String insertedGenre);
}