package com.example.movierental.services;

import com.example.movierental.model.Customer;
import com.example.movierental.model.Rental;

import java.util.List;

public interface UserService {
    //public abstract void registerUser(User user);
    public abstract List<Customer> getUsers();
    public abstract Customer findById(int userId);
    public abstract List<Rental> getCustomerMovies(int userId);
    public abstract void addMovie(int userId, Rental rental);
}