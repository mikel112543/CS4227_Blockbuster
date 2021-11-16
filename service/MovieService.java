package service;

import model.Movie;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public interface MovieService{
    public abstract ArrayList<Movie> listAllMovies() throws IOException;
    public abstract Movie createMovie(String [] metadata);
    public abstract Movie findByID(int movieID) throws IOException;
    public abstract ArrayList<Movie> findByName(String searchbar) throws IOException;
}