package com.example.movierental.services;

import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;


@Repository("users")
public class UserRepoServiceImpl implements UserRepoService {
    ArrayList<User> users = new ArrayList<>();
    final PasswordEncoder passwordEncoder;
    private User user;

    @Autowired
    public UserRepoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @PostConstruct
    public void getUsers() {
        String path = "users.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                users.add(new User(Integer.valueOf(values[0]),
                                    values[1], passwordEncoder.encode(values[2]),
                                    values[3], Integer.valueOf(values[4]),
                                    Integer.valueOf(values[5]),
                                    Boolean.valueOf(values[6])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByID(int i) {
        return users.get(i);
    }

    @Override
    public User findByUserName(String username) {
        User user = null;
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                user = findByID(i);
                break;
            }
        }
        return user;
    }
}