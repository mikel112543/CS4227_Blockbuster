package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.Rental;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static AbstractLogger chainLogger = RequesterClient.getChaining();
    private final List<User> users = new ArrayList<>();

    @Override
    @PostConstruct
    public void initializeList() {
        List<Rental> emptyRentals = new ArrayList<>();
        List<Rental> emptyRentals2 = new ArrayList<>();
        User test1 = new User(1, "Mike", "admin", false, 500, 2, emptyRentals, true);
        User test2 = new User(2, "Tom", "password", false, 250, 1, emptyRentals2, false);
        User test3 = new User(3, "Dick", "hello", false, 800, 2, emptyRentals, false);
        User test4 = new User(4, "Harry", "movies", false, 680, 3, emptyRentals, false);

        addUser(test1);
        addUser(test2);
        addUser(test3);
        addUser(test4);
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getUsers() {
        return users;
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
}
