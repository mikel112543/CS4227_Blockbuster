package com.example.movierental.service;

import com.example.movierental.exception.ServiceException;
import com.example.movierental.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        testUser = new User(9, "test", "test", "ROLE_USER", false);
        testUser.setTier(1);
        testUser.setLoyaltyPoints(300);
        userRepoService.addUser(testUser);
    }

    @AfterTestExecution
    public void tearDown() {
        userRepoService.removeUser(userRepoService.findByUserName("test"));
    }

    @Test
    @DisplayName("Should return user not found error")
    void userNotFound() {
        ServiceException exception = assertThrows(ServiceException.class, () -> userRepoService.findByUserName("brian"));
        assertEquals("2005", exception.getServiceError().getErrorCode());
    }

    @Test
    @DisplayName("User not found with ID provided")
    void idSearchFailure() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> userRepoService.findByID(50));
        assertEquals(new IndexOutOfBoundsException().getCause(), exception.getCause());
    }

    @Test
    @DisplayName("Should return register failure")
    void userNameAlreadyExists() {
        ServiceException exception = assertThrows(ServiceException.class, () ->
                userRepoService.registerUser("test", "test"));
        assertEquals("2005", exception.getServiceError().getErrorCode());
    }

    @Test
    @DisplayName("Should return Correct User")
    void getUserById() {
        User user = userRepoService.findByID(9);
        assertEquals("test", user.getUsername());
    }

/*    @Test
    @DisplayName("Should return current registered users")
    void getUsers() {
        List<User> mockRepo = userRepoService.getUsers();
        assertEquals("john", mockRepo.get(userRepoService.findByUserName("john").getUserID() - 1).getUsername());
        assertEquals("test", mockRepo.get(testUser.getUserID()).getPassword());
    }*/

    @Test
    @DisplayName("Should return user from username")
    void findByUserName() {
        assertEquals("test", userRepoService.findByUserName("test").getUsername());
    }


}
