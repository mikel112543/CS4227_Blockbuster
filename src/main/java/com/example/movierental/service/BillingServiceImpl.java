package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;

import com.example.movierental.logger.Dispatcher;
import com.example.movierental.logger.LoggerInterceptor;
import com.example.movierental.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(BillingServiceImpl.class);

    @Autowired
    UserRepoServiceImpl userRepoService;

    @Autowired
    Dispatcher dispatcher;


    @Autowired
    MailServiceImpl mailingService;

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
        mailingService.sendEmail(user.getEmailAddress(),
                "Bill number " + bill.getBillId(),
                "Here is your Bill information!"  + movie.getTitle() + "Rented for " + numberOfDays + "Cost: " + charge);
    }

    @Override
    public Bill findByBillID(int billID){
        for (Bill bill : listOfBills) {
            if (bill.getBillId() == billID) {
                return bill;
            }
        }
        dispatcher.logMessage(log, "Could not find bill", LoggerInterceptor.ERROR);
        throw new ServiceException(new ServiceError(Error.INVALID_BILL_ID));
    }
    @Override
    public List<Bill> getBills(){
        return listOfBills;
    }
}
