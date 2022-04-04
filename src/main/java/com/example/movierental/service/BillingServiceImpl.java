package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {
    ArrayList<Bill> listOfBills = new ArrayList<>();

    private static AbstractLogger chainLogger = RequesterClient.getChaining();

    @Autowired
    UserRepoServiceImpl userRepoService;

    @Override
    public void createBill(int userId, Rental rental){
        int billID = listOfBills.size()+1;
        User user = userRepoService.findByID(userId);
        String emailAddress = user.getEmailAddress();
        Bill bill = new Bill.BillBuilder(billID, rental, emailAddress).build();
        listOfBills.add(bill);
        Movie movie = rental.getMovie();
        int numberOfDays = rental.calculateRemainingDays();
        double charge = movie.getCharge();
    }

    @Override
    public Bill findByBillID(int billID){
        for (Bill bill : listOfBills) {
            if (bill.getBillId() == billID) {
                return bill;
            }
        }
        chainLogger.logMessage(AbstractLogger.ERROR_INFO, "Could not find bill");
        throw new ServiceException(new ServiceError(Error.INVALID_BILL_ID));
    }
    @Override
    public List<Bill> getBills(){
        return listOfBills;
    }
}
