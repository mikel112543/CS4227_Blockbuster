package com.example.movierental.service;


import com.example.movierental.model.User;

import java.util.List;

public interface AdminService {

    void addMovie(String title, String genre, String description, String length, int priceCode, String movieCoverUrl);

    void deleteMovie(int movieID);

    List<User> listAllUsers();

    void banCustomer(int userID);

    void unbanCustomer(int userID);

    void addDiscount(int userID);

    void removeDiscount(int userID);
}

