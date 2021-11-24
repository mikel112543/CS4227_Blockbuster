package com.example.movierental.controller;
import com.example.movierental.model.User;
import com.example.movierental.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller //CRUD
public class customerController {

    @Autowired
    UserServiceImpl userService;

    /**
     *
     * @param customerId find by customer ID
     * @return JSON Customer
     */
    @GetMapping(value = "/CustomerId/{CUSTOMER_ID}")
    public User getUser(@PathVariable("CUSTOMER_ID") final int customerId) {
        return userService.findByID(customerId);
    }
}
