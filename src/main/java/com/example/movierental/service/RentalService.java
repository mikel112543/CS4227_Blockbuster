package com.example.movierental.service;

import com.example.movierental.model.Rental;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface RentalService {
    Rental rentMovie(int customerId, int movieId);
    String removeRental(int customerId, int movieId);
    List<Rental> showRentals(int customerId);
}
