package com.example.movierental.service;

import com.example.movierental.model.Rental;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


/**
 * Author - Michael Danaher
 */
@Service
public interface RentalService {

    void initializeRentals();

    List<Rental> rentMovie(int customerId, int movieId);

    List<Rental> getRentals(int userId);

    List<Rental> getRental(int userId, int movieId);

    String removeRental(int customerId, int movieId);

    void checkRentals() throws IOException, ClassNotFoundException;

    List<ObjectNode> parseRentals(List<Rental> userRentals);
}
