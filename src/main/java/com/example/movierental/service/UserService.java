package com.example.movierental.service;

import com.example.movierental.model.Rental;
import com.example.movierental.model.User;

import java.util.List;

public interface UserService {
    //public abstract void registerUser(User user);
    List<User> getUsers();

    //void addUser(User user);

    void initializeListOfUsers();

    void rentMovie(int userId, Rental rental);

    List<Rental> getRentals(int userId);

    User findById(int userId);
}