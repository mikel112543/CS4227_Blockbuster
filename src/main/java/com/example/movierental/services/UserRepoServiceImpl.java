package com.example.movierental.services;

import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;


@Repository("users")
public class UserRepoServiceImpl implements UserRepoService {

    List<User> users = new ArrayList<>();

    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        String path = "users.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                User user = new User();
                user.setUserID(Integer.valueOf(values[0]));
                user.setUsername(values[1]);
                user.setPassword(passwordEncoder.encode(values[2]));
                user.setAuthority(values[3]);
                user.setLoyaltyPoints(Integer.valueOf(values[4]));
                user.setTier(Integer.valueOf(values[5]));
                user.setAccountNonLocked(Boolean.valueOf(values[6]));
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByID(int i) {
        this.getUsers();
        return users.get(i);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        this.getUsers();
        return users.stream().filter(user -> username.equals(user.getUsername())).findFirst();
    }
}