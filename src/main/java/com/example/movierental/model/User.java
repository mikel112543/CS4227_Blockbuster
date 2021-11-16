package com.example.movierental.model;

public abstract class User {

    private int userID, loyaltyPoints, tier;
    private String username, password;
    private boolean banned;
//  private List<movie> UserMovies = new ArrayList<movie>();

    public User() {
    }

    public User(int userID, String username, String password, Boolean banned, int loyaltyPoints, int tier) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.banned = banned;
        this.tier = tier;

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

    public int getTier() {
        return tier;
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

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "user{" +
                "userID=" + userID +
                ", loyaltyPoints=" + loyaltyPoints +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", banned=" + banned + '\'' +
                ", tier=" + tier +
                '}';
    }
}
