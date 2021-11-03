package controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    /**
     *
     * @param userId - Unique identifier attached to each of the users.
     * @return User - Returns object of type User
     */
    @GetMapping(value = "UserId/{userId}")
    public User getUser(@PathVariable("userId") final int userId) {
        return userService.getUser(userId);
    }

    /**
     *
     * @param userId - Unique identifier attached to each of the users.
     * @return User - Returns object of type user.
     */
    @DeleteMapping(value = "UserId/{userId}")
    public User deleteUser(@PathVariable("userId") final int userId) {
        return userService.deleteUser(userId);
    }
}
