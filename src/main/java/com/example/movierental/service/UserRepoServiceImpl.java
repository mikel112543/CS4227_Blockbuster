package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository("users")
public class UserRepoServiceImpl implements UserRepoService {


    private static AbstractLogger chainLogger = RequesterClient.getChaining();
    private List<User> listOfUsers = new ArrayList<User>();
    final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /*
    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    AdminServiceImpl adminService;

     */

    @Override
    public void initializeUsers() {
        String path = "users.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                User user = new User(Integer.valueOf(values[0]),
                                    values[1], passwordEncoder.encode(values[2]),
                                    values[3], Boolean.valueOf(values[6]));
                user.setLoyaltyPoints(Integer.valueOf(values[4]));
                user.setTier(Integer.valueOf(values[5]));
                listOfUsers.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        return listOfUsers;
    }

    @Override
    public User findByUserID(int userId) {
        User user;
        //Aaron: Can remove user and just return what is in For Loop
        for (User value : listOfUsers)
            if (value.getUserID() == userId) {
                user = value;
                return user;
            }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find user");
        throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
    }

    @Override
    public User findByUserName(String username) {
        User user = null;
        for(int i = 0; i < listOfUsers.size(); i++) {
            if (listOfUsers.get(i).getUsername().equals(username)) {
                user = findByUserID(i + 1);
                break;
            }
        }
        return user;
    }

    public void registerUser(String userName, String password) {
        int userId = listOfUsers.size();
        passwordEncoder.encode(password);
        User user = new User(userId, userName, password, "ROLE_USER", false);
        listOfUsers.add(user);
    }

    @Override
    public void addUser(User user) {
        listOfUsers.add(user);
    }
}