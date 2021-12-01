package com.example.movierental.service;

import com.example.movierental.model.Rental;
import com.example.movierental.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User findByID(int i);

    void addUser(User user);

    void initializeList();

    List<Rental> rentMovie(int userId, Rental rental);

    List<Rental> getRentals(int userId);
}