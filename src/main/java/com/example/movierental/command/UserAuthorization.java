package com.example.movierental.command;

import com.example.movierental.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Invoker Class
@Component
public class UserAuthorization {
    AdminServiceImpl adminService;

    @Autowired
    public UserAuthorization(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    public void banUser(int id) {
        adminService.banCustomer(id);
    }

    public void unbanUser(int id) {
        adminService.unbanCustomer(id);
    }
}
