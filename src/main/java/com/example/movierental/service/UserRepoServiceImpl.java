package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.Dispatcher;
import com.example.movierental.logger.LoggerInterceptor;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Repository("users")
public class UserRepoServiceImpl implements UserRepoService {

    Dispatcher dispatcher;
    List<User> users = new ArrayList<>();
    final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(UserRepoServiceImpl.class);

    @Autowired
    public UserRepoServiceImpl(PasswordEncoder passwordEncoder, Dispatcher dispatcher) {
        this.passwordEncoder = passwordEncoder;
        this.dispatcher = dispatcher;
    }

    @Override
    public void initializeUsers() throws IOException {
        String path = "users.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                User user = new User(Integer.parseInt(values[0]),
                        values[1], passwordEncoder.encode(values[2]),
                        values[3], Boolean.parseBoolean(values[6]));
                user.setLoyaltyPoints(Integer.parseInt(values[4]));
                user.setTier(Integer.parseInt(values[5]));
                user.setDiscount(Boolean.parseBoolean(values[7]));
                users.add(user);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            dispatcher.logMessage(log, "File not found", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.FILE_NOT_FOUND));
        } catch (IOException e) {
            dispatcher.logMessage(log, "Initialization Error", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.INVALID_INITIALIZATION));
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User findByID(int i) {
        User user = users.get(i - 1);
        if (user == null) {
            dispatcher.logMessage(log, "Could not find user", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.INVALID_USER_ID));
        } else {
            return user;
        }
    }

    @Override
    public User findByUserName(String username) {
        User user = null;
        for (User value : users) {
            if (value.getUsername().equals(username)) {
                user = value;
                break;
            }
        }
        if (user == null) {
            dispatcher.logMessage(log, "Username not recognized", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.INVALID_USERNAME));
        }
        return user;
    }

    @Override
    public void registerUser(String userName, String password) {
        int userId = users.size() + 1;
        for (User user : users) {
            if (Objects.equals(user.getUsername(), userName)) {
                dispatcher.logMessage(log, "Could not register user as username already exists", LoggerInterceptor.ERROR);
                throw new ServiceException(new ServiceError(Error.INVALID_USERNAME));
            }
        }
        try {
            dispatcher.logMessage(log, "Registering user...", LoggerInterceptor.INFO);
            User user = new User(userId, userName, passwordEncoder.encode(password), "ROLE_USER", false);
            users.add(user);
        } catch (ServiceException e) {
            throw new ServiceException(new ServiceError(Error.INVALID_REGISTER));
        }
        dispatcher.logMessage(log, "Registration successful", LoggerInterceptor.INFO);
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }


    /**
     * Method uses Java imports to get the IP address of the local host system.
     * Using this to get the Region of the User and show Movies Available to them.
     * @return userAddress : IP Address
     * @author Bandile -18246923
     */
    @Override
    public String getUserIPAddress() {
        String userAddress;
        try {
            //Local System IP Address
            InetAddress ia = InetAddress.getLocalHost();
            userAddress = ia.getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        userAddress = "109.79.29.175";

        return userAddress;
    }

    @Override
    public String getCountry() throws IOException, GeoIp2Exception {
        String ia = getUserIPAddress();

        WebServiceClient client = new WebServiceClient.Builder(700850, "2aTLMY4H1sQGjT44").host("geolite.info")
                .build();
        InetAddress ipAddress = InetAddress.getByName(ia);
        // Do the lookup
        CountryResponse response = client.country(ipAddress);
        Country country = response.getCountry();
        return country.toString();
    }

}