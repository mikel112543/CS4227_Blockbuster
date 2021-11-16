package com.example.movierental.services;

import com.example.movierental.model.Admin;
import com.example.movierental.model.Customer;
import com.example.movierental.model.User;

import java.util.List;

public interface UserService {
    //public abstract void registerUser(User user);
    public abstract List<Customer> getCustomers();
    public abstract Customer findByID(int i);
    public abstract Customer findByUserName(String user);
}
