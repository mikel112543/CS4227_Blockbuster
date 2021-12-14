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
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Repository("users")
public class UserRepoServiceImpl implements UserRepoService {


    private static AbstractLogger chainLogger = RequesterClient.getChaining();
    List<User> users = new ArrayList<>();
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "File not found");
            throw new ServiceException(new ServiceError(Error.FILE_NOT_FOUND));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User findByID(int i) {
        if(users.get(i - 1) == null) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find user");
            throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
        } else {
            return users.get(i - 1);
        }
    }

    @Override
    public User findByUserName(String username) {
        User user = null;
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                user = findByID(i + 1);
                break;
            }
        }
        if(user == null) {
            chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Username not found");
            throw new ServiceException(new ServiceError(Error.INVALID_LOGIN_CREDENTIALS));
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        users.add(user.getUserID() - 1 , user);
    }
}