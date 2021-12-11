package com.example.movierental.service;

import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepoServiceImpl userRepoService;

    @Autowired
    public UserDetailsServiceImpl(@Qualifier("users")UserRepoServiceImpl userRepoService) {
        this.userRepoService = userRepoService;
    }

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepoService.findByUserName(userName);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(String.format("Username %s not found", userName));
        }
    }
}
