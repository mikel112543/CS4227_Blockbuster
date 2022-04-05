package com.example.movierental.service;


import com.example.movierental.model.User;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    void addMovie(String title, String genre, String description, String length, int priceCode, String movieCoverUrl) throws IOException, GeoIp2Exception;

    void deleteMovie(int movieID);

    List<User> listAllUsers();

    void banCustomer(int userID);

    void unbanCustomer(int userID);

    void addDiscount(int userID);

    void removeDiscount(int userID);
}

