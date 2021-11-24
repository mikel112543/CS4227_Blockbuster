package com.example.movierental.services;

import com.example.movierental.model.Admin;
import com.example.movierental.model.Customer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements com.example.movierental.services.UserService {
    List<Customer> customers = new ArrayList<>();
    List<Admin> admins = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        String path = "users.csv";
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Customer cust = new Customer();
                cust.setUserID(Integer.valueOf(values[0]));
                cust.setUsername(values[1]);
                cust.setPassword(values[2]);
                cust.setBanned(Boolean.valueOf(values[3]));
                cust.setLoyaltyPoints(Integer.valueOf(values[4]));
                cust.setTier(Integer.valueOf(values[5]));
                customers.add(cust);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }


    @Override
    public Customer findByID(int i) {
        this.getCustomers();
        Customer cust = new Customer();
        for(int j = 0; j < customers.size(); j++) {
            if (i == j + 1) {
                cust = customers.get(j);
            }
        }
        return cust;
    }

    @Override
    public Customer findByUserName(String user) {
        customers = this.getCustomers();
        Customer cust = new Customer();
        for(int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUsername().equals(user)) {
                cust = customers.get(i);
                return cust;
            } else {
                return null;
            }
        }
        return cust;
    }
}

/*@Override
    public List<Customer> getCustomers() {
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
    @Override
    public List<Admin> getAdmins() {
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
    }*/