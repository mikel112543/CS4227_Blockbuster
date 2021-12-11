package com.example.movierental;

import com.example.movierental.model.User;
import com.example.movierental.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class loginTest {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void testFindByUser() {
        User user = userDetailsService.loadUserByUsername("admin");
        System.out.println(user.toString());
    }
}
