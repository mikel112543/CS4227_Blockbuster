package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.ErrorLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Rental;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.springframework.stereotype.Service;

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


    @Override
    public void initializeList() {
        User test1 = new User(1, "Mike");
        User test2 = new User(2, "Tom");
        User test3 = new User(3, "Dick");
        User test4 = new User(4, "Harry");

        addUser(test1);
        addUser(test2);
        addUser(test3);
        addUser(test4);
    }

    @Override
    public void addUser(User user) {
        users.add(user);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public User findByID(int userId) {

        User test1 = new User(1, "Mike");
        User test2 = new User(2, "Tom");
        User test3 = new User(3, "Dick");
        User test4 = new User(4, "Harry");

        addUser(test1);
        addUser(test2);
        addUser(test3);
        addUser(test4);

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
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(userName)) {
                return users.get(i);
            }
            i++;
        }
        return null;
    }

    @Override
    public List<Rental> addMovie(int userId, Rental rental) {
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

/*@Override
    public List<Customer> getCustomers() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("users.csv")).withSkipLines(1).build();
            customers = reader.readAll().stream().map(data -> {
                Customer cust = new Customer();
                cust.setUserID(Integer.valueOf(data[0]));
                cust.setUsername(data[1]);
                cust.setPassword(data[2]);
                cust.setBanned(Boolean.valueOf(data[3]));
                cust.setTier(Integer.valueOf(data[4]));
                return cust;
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return customers;
    }
    @Override
    public List<Admin> getAdmins() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("admins.csv")).withSkipLines(1).build();
            admins = reader.readAll().stream().map(data -> {
                Admin admin = new Admin();
                admin.setUserID(Integer.valueOf(data[0]));
                admin.setUsername(data[1]);
                admin.setPassword(data[2]);
                admin.setBanned(Boolean.valueOf(data[3]));
                admin.setTier(Integer.valueOf(data[4]));
                return admin;
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return admins;
    }*/