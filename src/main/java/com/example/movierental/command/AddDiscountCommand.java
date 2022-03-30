package com.example.movierental.command;

import org.springframework.stereotype.Component;

//Concrete Class
@Component
public class AddDiscountCommand implements Command {

    final UserDiscount userDiscount;

    public AddDiscountCommand(UserDiscount userDiscount) {
        this.userDiscount = userDiscount;
    }

    @Override
    public void execute(int id) {
        userDiscount.addDiscount(id);
    }
}
