package com.example.movierental.service;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RentalServiceImplTest {

    private final List<Rental> mockRentals = new ArrayList<>();
    private User mockUser = new User(99, "test", "test", false, 26, 2, mockRentals, false);

    @Autowired
    RentalServiceImpl rentalService;

    @Autowired
    UserServiceImpl userService;

    @Test
    void movieAlreadyRented() {
        rentalService.rentMovie(1, 1);
        assertThrows(ServiceException.class, () -> {
            rentalService.rentMovie(1, 1);
        });
    }

    @Test
    void userRentalsEmpty() {
        assertThrows(ServiceException.class, () -> {
            rentalService.getRentals(99);
        });
    }

    @Test
    void getRental() {
    }

    @Test
    void removeRental() {
    }
}