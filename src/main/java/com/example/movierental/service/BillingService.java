package com.example.movierental.service;

import com.example.movierental.model.Bill;
import com.example.movierental.model.Rental;

import java.util.ArrayList;

public interface BillingService {

    ArrayList<Bill> getBills();

    Bill findByBillID(int billID);

    void createBill(int userId, Rental rental);

}
