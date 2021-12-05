package com.example.movierental.service;


import com.example.movierental.model.User;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public interface AdminService {
    void addMovie(String title, String genre, String description, double length, int priceCode, int movieRating);

    void deleteMovie(int movieID);

    List<User> listAllUsers();

    User createCustomer(String[] metadata);

    void banCustomer(int userID) throws IOException;
}