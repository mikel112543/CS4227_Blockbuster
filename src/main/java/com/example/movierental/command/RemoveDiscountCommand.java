package com.example.movierental.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Concrete Class
@Component
public class RemoveDiscountCommand implements Command {

    final UserDiscount userDiscount;

    public RemoveDiscountCommand(UserDiscount userDiscount) {
        this.userDiscount = userDiscount;
    }

    @Override
    public void execute(int id) {
        userDiscount.removeUserDiscount(id);
    }
}
