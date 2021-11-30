package com.example.movierental.service;


import com.example.movierental.model.Movie;

import java.util.ArrayList;

public interface MovieService{
    public abstract ArrayList<Movie> listAllMovies();
    public abstract Movie findByMovieID(int movieID);
    public abstract ArrayList<Movie> findByName(String searchbar);
}