package com.example.movierental.service;

import com.example.movierental.model.Rental;
import org.springframework.stereotype.Service;


@Service
public interface RentalService {
    public void rentMovie(int customerId, int  movieId);
    public void removeRental(int customerId, int movieId);
}
