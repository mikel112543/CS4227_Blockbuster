package com.example.movierental;

import com.example.movierental.model.User;
import com.example.movierental.service.MovieServiceImpl;
import com.example.movierental.service.RentalServiceImpl;
import com.example.movierental.service.UserRepoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MovieRentalApplication {

    public static void main(String[] args) {
       /* PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder(10);
        UserRepoServiceImpl userRepoService = new UserRepoServiceImpl(passwordEncoder);
        userRepoService.InitializeUsers();
        MovieServiceImpl movieService = new MovieServiceImpl();
        movieService.InitializeList();
        RentalServiceImpl rentalService = new RentalServiceImpl(userRepoService, movieService);
        rentalService.rentMovie(2, 1);
        rentalService.rentMovie(3,2);
        rentalService.rentMovie(4,3);
        rentalService.rentMovie(4,4);*/
        SpringApplication.run(MovieRentalApplication.class, args);
    }
}
