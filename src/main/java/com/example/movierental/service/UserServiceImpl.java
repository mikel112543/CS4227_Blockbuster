package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
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

    @Autowired
    MovieServiceImpl movieService;

    private static AbstractLogger chainLogger = RequesterClient.getChaining();
    private final List<User> users = new ArrayList<>();

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
    public User findByID(int userId) {
        User user;
        //Aaron: Can remove user and just return what is in For Loop
        for (User value : users)
            if (value.getUserID() == userId) {
                user = value;
                return user;
            }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find user");
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

    @Override
    public List<Rental> getRentals(int userId) {
        User user = findById(userId);
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
        List<Rental> userRentals = new ArrayList<Rental>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((br.readLine() != null)){
                String [] values = line.split(",");
                User user = new User();
                user.setUserID(Integer.parseInt(values[0]));
                user.setUsername(values[1]);
                user.setPassword(values[2]);
                user.setAuthority(values[3]);
                user.setLoyaltyPoints(Integer.parseInt(values[4]));
                user.setTier(Integer.parseInt(values[5]));
                user.setAccountNonLocked(Boolean.parseBoolean(values[6]));
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
