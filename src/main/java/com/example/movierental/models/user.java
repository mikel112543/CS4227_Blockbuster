package com.example.movierental.models;

public abstract class user {

    private int userID, loyaltyPoints;
    private String username, password;
    private boolean banned;
//  private List<movie> UserMovies = new ArrayList<movie>();

    public user() {
    }

    public user(int userID, String username, String password, Boolean banned, int loyaltyPoints) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.banned = banned;
    }

    public int getUserID() {
        return userID;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
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

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
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
                ", loyaltyPoints=" + loyaltyPoints +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", banned=" + banned +
                '}';
    }
}
