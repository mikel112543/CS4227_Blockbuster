package com.example.movierental.service;

import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import com.example.movierental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    AdminServiceImpl adminService;

    List<User> ListOfUsers = new ArrayList<User>();
    List<Movie> ListOfMovies = new ArrayList<Movie>();
    List<Rental> ListOfRentals = new ArrayList<Rental>();

    @Override
    public List<User> getUsers() {
        return ListOfUsers;
    }

    @Override
    public User findById(int userId) {
        User user = new User();
        for (int i = 0 ; i < ListOfUsers.size() ; i++ ) {
            if (ListOfUsers.get(i).getUserID() == userId) {
                user = ListOfUsers.get(i);
            }
        }
        return user;
    }
    @Override
    public List<Rental> getCustomerMovies(int customerId) {
        return ListOfRentals;
    }

    //adds movie to the rentalslist.
    @Override
    public void rentMovie(Rental rental){
        ListOfRentals.add(rental);
    }

}
