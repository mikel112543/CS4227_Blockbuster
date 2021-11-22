package service;

import model.User;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public interface AdminService {
    public abstract void addMovie(String title, String genre, String description, LocalTime movieLength, LocalTime rentLength, double price, int movieID, int movieRating, String movieRelease) throws IOException;
    public abstract void deleteMovie(int movieID) throws IOException;
    public abstract ArrayList<User> listAllUsers() throws IOException;
    public abstract User createUser(String [] metadata);
    public abstract void banUser(int userID) throws IOException;
}