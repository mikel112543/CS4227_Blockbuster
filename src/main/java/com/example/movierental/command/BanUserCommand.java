package com.example.movierental.command;

import org.springframework.stereotype.Component;

//Concrete Class
@Component
public class BanUserCommand implements Command {

    final UserAuthorization userAuthorization;

    public BanUserCommand(UserAuthorization userAuthorization) {
        this.userAuthorization = userAuthorization;
    }

    @Override
    public void execute(int id) {
        userAuthorization.banUser(id);
    }
}

