package com.example.movierental.service;

import com.example.movierental.model.Rental;
import com.example.movierental.model.User;

import java.util.List;

public interface UserService {
    //public abstract void registerUser(User user);
    List<User> getCustomers();

    User findByID(int i);

    void addUser(User user);

    void initializeList();

    User findByUserName(String user);

    List<Rental> rentMovie(int userId, Rental rental);

    List<Rental> getRentals(int userId);
}