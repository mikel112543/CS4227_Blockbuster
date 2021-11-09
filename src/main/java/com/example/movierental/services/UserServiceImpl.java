package com.example.movierental.services;

import com.example.movierental.model.Customer;
import com.example.movierental.model.Admin;
import com.example.movierental.model.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    List<Customer> customers = new ArrayList<>();
    List<Admin> admins = new ArrayList<>();

/*    @Override
    public void registerUser(User user) {
        
    }*/

    @Override
    public void updateUser(String id, User user) {
    }

    private List<Customer> getCustomers() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("users.csv")).withSkipLines(1).build();
            customers = reader.readAll().stream().map(data -> {
                Customer cust = new Customer();
                cust.setUserID(Integer.valueOf(data[0]));
                cust.setUsername(data[1]);
                cust.setPassword(data[2]);
                cust.setBanned(Boolean.valueOf(data[3]));
                cust.setTier(Integer.valueOf(data[4]));
                return cust;
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private List<Admin> getAdmins() {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("admins.csv")).withSkipLines(1).build();
            admins = reader.readAll().stream().map(data -> {
                Admin admin = new Admin();
                admin.setUserID(Integer.valueOf(data[0]));
                admin.setUsername(data[1]);
                admin.setPassword(data[2]);
                admin.setBanned(Boolean.valueOf(data[3]));
                admin.setTier(Integer.valueOf(data[4]));
                return admin;
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public Customer findByID(int i) {
        Customer cust = new Customer();
        for(int j = 0; j < customers.size(); j++) {
            if (i == j + 1) {
                cust = customers.get(j);
            }
        }
        return cust;
    }

    public Customer findByUserName(String user) {
        Customer cust = new Customer();
        for(int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUsername() == user) {
                cust = customers.get(i);
            } else {
                cust = null;
            }
        }
        return cust;
    }

}
