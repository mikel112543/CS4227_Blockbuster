package com.example.movierental.service;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.Dispatcher;
import com.example.movierental.logger.LoggerInterceptor;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Dispatcher dispatcher;
    UserRepoServiceImpl userRepoService;
    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public UserDetailsServiceImpl(@Qualifier("users")UserRepoServiceImpl userRepoService, Dispatcher dispatcher) {
        this.userRepoService = userRepoService;
        this.dispatcher = dispatcher;
    }

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepoService.findByUserName(userName);
        if (user != null) {
            return user;
        } else {
            dispatcher.logMessage(log, "Could not load user", LoggerInterceptor.ERROR);
            throw new ServiceException(new ServiceError(Error.USER_LOAD_FAILURE));
        }
    }

}
