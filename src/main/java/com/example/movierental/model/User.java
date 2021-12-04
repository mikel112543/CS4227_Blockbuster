package com.example.movierental.model;


public class User implements UserDetails {

    @JsonIgnore
    private int userID;

    @JsonProperty("Username")
    private String username;

    @JsonIgnore
    private String password;

    @JsonProperty("Banned")
    private boolean banned;

    @JsonProperty("Loyalty Points")
    private int loyaltyPoints;

    @JsonProperty("Tier")
    private int tier;

    @JsonIgnore
    private List<Rental> rentedMovies;

    @JsonProperty("Is Admin")
    private boolean isAdmin;

    private int userID, loyaltyPoints, tier;
    private String username, password, authority;
    private boolean isAccountNonLocked, isEnabled;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public User(int userID, String username, String password, boolean banned) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.loyaltyPoints = loyaltyPoints;
        this.tier = tier;
        this.isAccountNonLocked = isAccountNonLocked;
        this.rentedMovies = rentedMovies;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

//    public void setLoyaltyPoints(int loyaltyPoints) {
//        this.loyaltyPoints = loyaltyPoints;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void setAuthority(String authority) { this.authority = authority;}

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public List<Rental> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(List<Rental> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                '}';
    }
}