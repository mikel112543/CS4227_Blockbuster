package com.example.movierental.services;

import com.example.movierental.models.User;

public interface UserService {
    //public abstract void registerUser(User user);
    public abstract void updateUser(String id, User user);
}
