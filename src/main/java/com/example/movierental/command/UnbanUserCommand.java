package com.example.movierental.command;

import com.example.movierental.model.User;
import com.example.movierental.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Component;

//Concrete Class
@Component
public class UnbanUserCommand implements Command {

    final UserAuthorization userAuthorization;

    public UnbanUserCommand(UserAuthorization auth) {
        userAuthorization = auth;
    }

    @Override
    public void execute(int id) {
        userAuthorization.unbanUser(id);
    }
}
