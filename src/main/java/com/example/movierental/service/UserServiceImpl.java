package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Rental;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    MovieServiceImpl movieService;

    private static AbstractLogger chainLogger = RequesterClient.getChaining();

    @Autowired
    AdminServiceImpl adminService;

    List<User> ListOfUsers = new ArrayList<User>();

    @Override
    public List<User> getUsers() {
        //initializeListOfUsers will be ran first. so ListOfUsers isn't empty.
        return ListOfUsers;
    }

    @Override
    public User findByUserID(int userId) {
        User user;
        //Aaron: Can remove user and just return what is in For Loop
        for (User value : ListOfUsers)
            if (value.getUserID() == userId) {
                user = value;
                return user;
            }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find user");
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

    @Override
    public List<Rental> getRentals(int userId) {
        User user = findByUserID(userId);
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
    public void initializeListOfUsers(){
        String path = "users.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((br.readLine() != null)){
                List<Rental> userRentals = new ArrayList<Rental>();
                String [] values = line.split(",");
                User user = new User();
                user.setUserID(Integer.parseInt(values[0]));
                user.setUsername(values[1]);
                user.setPassword(values[2]);
                user.setAdmin(Boolean.parseBoolean(values[3]));
                user.setLoyaltyPoints(Integer.parseInt(values[4]));
                user.setTier(Integer.parseInt(values[5]));
                user.setBanned(Boolean.parseBoolean(values[6]));
                user.setRentedMovies(userRentals);
                ListOfUsers.add(user);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
