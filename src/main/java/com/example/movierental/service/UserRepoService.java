package com.example.movierental.service;

import com.example.movierental.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserRepoService {

    List<User> getUsers();

    void addUser(User user);

    void initializeUsers() throws IOException;

    User findByID(int i);

    User findByUserName(String username);
}