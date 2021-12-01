package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Rental;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import com.example.movierental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static AbstractLogger chainLogger = RequesterClient.getChaining();
    private final List<User> users = new ArrayList<>();

    @Autowired
    AdminServiceImpl adminService;

    @Override
    @PostConstruct
    public void initializeList() {
        List<Rental> emptyRentals = new ArrayList<>();
        User test1 = new User(1, "Mike", "admin", false, 500, 2, emptyRentals, true);
        User test2 = new User(2, "Tom", "password", false, 250, 1, emptyRentals, false);
        User test3 = new User(3, "Dick", "hello", false, 800, 2, emptyRentals, false);
        User test4 = new User(4, "Harry", "movies", false, 680, 3, emptyRentals, false);

        addUser(test1);
        addUser(test2);
        addUser(test3);
        addUser(test4);
    }

    @Override
    public List<User> getUsers() {
        return ListOfUsers;
    }

    @Override
    public List<Rental> getRentals(int userId) {
        User user = findByID(userId);
        List<Rental> userRentals;
        try {
            userRentals = user.getRentedMovies();
        } catch (Exception e) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find User Rentals");
            throw new ServiceException(new ServiceError(Error.INVALID_USER_RENTALS));
        }
        return userRentals;
    }

    @Override
    public List<User> getCustomers() {
        String path = "users.csv";
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                User cust = new User();
                cust.setUserID(Integer.valueOf(values[0]));
                cust.setUsername(values[1]);
                cust.setPassword(values[2]);
                cust.setBanned(Boolean.valueOf(values[3]));
                cust.setLoyaltyPoints(Integer.valueOf(values[4]));
                cust.setTier(Integer.valueOf(values[5]));
                users.add(cust);
            }
        }
        return users;
    }


    @Override
    public User findByID(int userId) {
        User user;
        for (User value : users)
            if (value.getUserID() == userId) {
                user = value;
                return user;
            }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find user");
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

    @Override
    public User findByUserName(String userName) {
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Rental> rentMovie(int userId, Rental rental) {
        User user = findByID(userId);
        try {
            user.getRentedMovies().add(rental);
        } catch (Exception e) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Movie could not be rented");
            throw new ServiceException(new ServiceError(Error.GENERAL_ERROR));
        }
        return user.getRentedMovies();
    }
}
