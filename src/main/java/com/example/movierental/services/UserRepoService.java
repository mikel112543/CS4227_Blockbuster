package com.example.movierental.services;

import com.example.movierental.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepoService {
    void getUsers();
    User findByID(int i);
    User findByUserName(String userName);
}
