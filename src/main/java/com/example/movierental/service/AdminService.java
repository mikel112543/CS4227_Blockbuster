package com.example.movierental.service;


import com.example.movierental.model.User;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public interface AdminService {
    public abstract void addMovie(String title, String genre, String description, Duration length, int price, int movieID, int movieRating) throws IOException;
    public abstract void deleteMovie(int movieID) throws IOException;
    public abstract ArrayList<User> listAllUsers() throws IOException;
    public abstract User createCustomer(String [] metadata);
    public abstract void banCustomer(int userID) throws IOException;
}