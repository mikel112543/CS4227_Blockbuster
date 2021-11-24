package com.example.movierental.service;

import model.Movie;

import java.io.IOException;
import java.util.ArrayList;

public interface MovieService{
    public abstract ArrayList<Movie> listAllMovies() throws IOException;
    public abstract Movie createMovie(String [] metadata);
    public abstract Movie findByMovieID(int movieID) throws IOException;
    public abstract ArrayList<Movie> findByName(String searchbar) throws IOException;
}