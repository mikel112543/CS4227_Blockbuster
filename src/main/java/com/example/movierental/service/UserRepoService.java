package com.example.movierental.service;

import com.example.movierental.model.User;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.util.List;

public interface UserRepoService {

    List<User> getUsers();

    void addUser(User user);

    void removeUser(User user);

    void initializeUsers() throws IOException;

    User findByID(int i);

    User findByUserName(String username);

    void registerUser(String userName, String password);

    String getUserIPAddress();

    String getCountry() throws IOException, GeoIp2Exception;
}