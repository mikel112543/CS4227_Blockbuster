package com.example.movierental;

import com.example.movierental.model.User;
import com.example.movierental.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MovieRentalApplication {

    public static void main(String[] args) {

        SpringApplication.run(MovieRentalApplication.class, args);

        UserServiceImpl userService = new UserServiceImpl();

        User test1 = new User(1, "Mike");
        User test2 = new User(2, "Tom");
        User test3 = new User(3, "Dick");
        User test4 = new User(4, "Harry");

        userService.addUser(test1);
        userService.addUser(test2);
        userService.addUser(test3);
        userService.addUser(test4);
    }
}
