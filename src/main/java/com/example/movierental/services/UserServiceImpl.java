package com.example.movierental.services;

import com.example.movierental.models.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

/*    @Override
    public void registerUser(User user) {
        
    }*/

    @Override
    public void updateUser(String id, User user) {
    }

    private List<User> getUsers(Boolean ad) {
        List listOfUsers = Collections.emptyList();
        try{
            if(ad == false) {
                CSVReader reader = new CSVReaderBuilder(new FileReader("users.csv")).withSkipLines(1).build();
                listOfUsers = reader.readAll().stream().map(data-> {
                    Customer cust = new User();
                    cust.setUserID(data[0]);
                    cust.setUsername(data[1]);
                    cust.setPassword(data[2]);
                    cust.setBanned(data[3]);
                    cust.setTier(data[4]);
                    return cust;
                }).collect(Collectors.toList());
                        return listOfUsers;
            } else {
                CSVReader reader = new CSVReaderBuilder(new FileReader("admins.csv")).withSkipLines(1).build();
                listOfUsers = reader.readAll().stream().map(data-> {
                Admin admin = new User();
                admin.setUserID(data[0]);
                admin.setUsername(data[1]);
                admin.setPassword(data[2]);
                admin.setBanned(data[3]);
                admin.setTier(data[4]);
                return admin;
            }).collect(Collectors.toList());
            return listOfUsers;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }
}
