package com.example.movierental.service;

import com.example.movierental.model.User;

import java.util.List;

public interface UserRepoService {
    void InitializeUsers();
    List<User> getUsers();
    User findByID(int i);
    User findByUserName(String userName);
}
