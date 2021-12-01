package com.example.movierental.service;


import com.example.movierental.model.User;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public interface AdminService {
    void addMovie(int movieID, String title, String genre, String description, String length, int price, int movieRating) throws IOException;

    void deleteMovie(int movieID) throws IOException;

    ArrayList<User> listAllUsers() throws IOException;

    User createCustomer(String[] metadata);

    void banCustomer(int userID) throws IOException;
}