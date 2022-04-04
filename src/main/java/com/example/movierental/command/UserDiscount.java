package com.example.movierental.command;

import com.example.movierental.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Invoker
@Component
public class UserDiscount {
    AdminServiceImpl adminService;

    @Autowired
    public UserDiscount(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    public void addDiscount(int id) {
        adminService.addDiscount(id);
    }

    public void removeDiscount(int id) {
        adminService.removeDiscount(id);
    }
}
