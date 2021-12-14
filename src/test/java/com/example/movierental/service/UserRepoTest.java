package com.example.movierental.service;

import com.example.movierental.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("User-Repo Service Tests")
class UserRepoTest {
    @Autowired
    UserRepoServiceImpl userRepoService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private User testUser;

    @BeforeEach
    public void setUp() {
        List<User> mockUserRepo = new ArrayList<>();
        testUser = new User(55, "test", "test", "ROLE_USER", false);
        testUser.setTier(1);
        testUser.setLoyaltyPoints(300);
        userRepoService.addUser(testUser);
    }

    @AfterTestExecution
    public void tearDown() {
        userRepoService.removeUser(userRepoService.findByUserName("test"));
    }
}
