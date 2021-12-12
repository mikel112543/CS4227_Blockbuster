package com.example.movierental.service;

import com.example.movierental.model.Rental;
import com.example.movierental.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void addUser(User user);

    void initializeListOfUsers();

    List<Rental> getRentals(int userId);

    User findByUserID(int userId);
}