package com.example.movierental;

import com.example.movierental.model.User;
import com.example.movierental.services.UserDetailsServiceImpl;
import com.example.movierental.services.UserRepoService;
import com.example.movierental.services.UserRepoServiceImpl;
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
