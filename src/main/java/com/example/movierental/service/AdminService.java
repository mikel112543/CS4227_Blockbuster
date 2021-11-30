package com.example.movierental.service;


import com.example.movierental.model.User;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public interface AdminService {
    void addMovie(String title, String genre, String description, Duration length, int price, int movieID, int movieRating) throws IOException;

    void deleteMovie(int movieID) throws IOException;

    ArrayList<User> listAllUsers() throws IOException;

    User createCustomer(String[] metadata);

    void banCustomer(int userID) throws IOException;
}