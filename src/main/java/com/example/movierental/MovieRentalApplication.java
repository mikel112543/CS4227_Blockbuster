package com.example.movierental;

import com.example.movierental.model.Customer;
import com.example.movierental.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MovieRentalApplication {

    public static void main(String[] args) {

        SpringApplication.run(MovieRentalApplication.class, args);

        User mike = new Customer(121345654, 5);
        User Tom = new Customer(987654346, 7);
        User jim = new Customer(123454, 123434);
        jim.setLoyaltyPoints(5);

        List<User> customers = new ArrayList<User>();
        customers.add(Tom);
        customers.add(mike);

    }

}
