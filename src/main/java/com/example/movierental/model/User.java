package com.example.movierental.model;

public abstract class User {

    private int userID;
    private String username, password;
    private boolean banned;

    public User() {

    }

    //loyalty points taken out
    public User(int userID, String username, String password, boolean banned) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.banned = banned;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "user{" +
                "userID=" + userID +
                //", loyaltyPoints=" + loyaltyPoints +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", banned=" + banned +
                '}';
    }
}
