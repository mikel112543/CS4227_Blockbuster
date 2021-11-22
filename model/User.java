package model;

public class User{
    private int userID;
    private String username, password;
    private boolean isBanned;

    public User(int userID, String username, String password, boolean isBanned) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.isBanned = isBanned;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getisBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isBanned=" + isBanned +
                '}';
    }
}