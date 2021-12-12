package com.example.movierental.service;

import com.example.movierental.model.Rental;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface RentalService {
    List<Rental> rentMovie(int customerId, int movieId);

    List<Rental> getRentals(int userId);

    Rental getRental(int userId, int movieId);

    String removeRental(int customerId, int movieId);

    void checkRentals();
}
