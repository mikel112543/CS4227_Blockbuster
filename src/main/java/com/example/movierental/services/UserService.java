package com.example.movierental.services;

import com.example.movierental.model.User;

public interface UserService {
    //public abstract void registerUser(User user);
    public abstract void updateUser(String id, User user);
}
