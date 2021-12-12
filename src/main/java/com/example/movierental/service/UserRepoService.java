package com.example.movierental.service;

import com.example.movierental.model.User;

import java.util.List;

public interface UserRepoService {

    List<User> getUsers();

    void addUser(User user);

    void initializeUsers();

    User findByUserID(int userId);

    User findByUserName(String username);
}