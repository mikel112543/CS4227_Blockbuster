package com.example.movierental;

import com.example.movierental.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MovieRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRentalApplication.class, args);
    }
}
