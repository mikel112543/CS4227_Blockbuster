package com.example.movierental.service;


import com.example.movierental.model.User;

import java.util.ArrayList;

public interface AdminService {

    void addMovie(String title, String genre, String description, String length, int price, int movieID);

    void deleteMovie(int movieID);

    ArrayList<User> listAllUsers();

    void banCustomer(int userID);
}