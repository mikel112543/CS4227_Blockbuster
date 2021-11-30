package com.example.movierental.services;

import com.example.movierental.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepoService {
    List<User> getUsers();
    User findByID(int i);
    Optional<User> findByUserName(String userName);
}
