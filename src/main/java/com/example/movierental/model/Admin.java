package com.example.movierental.model;

public class Admin extends User{

    public Admin() {
        //empty constructor
    }

    public Admin(int userID, String username, String password, boolean banned) {
        super(userID, username, password, banned);
    }
}
