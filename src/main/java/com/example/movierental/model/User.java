package com.example.movierental.model;


import com.example.movierental.states.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class User implements UserDetails {

    /*//@JsonIgnore
    private int userID;

    @JsonProperty("Username")
    private String username;

    //@JsonIgnore
    private String password;

    @JsonProperty("Authority")
    private String authority;

    @JsonProperty("Banned")
    private boolean isAccountNonLocked; // False for banned

    @JsonProperty("Loyalty Points")
    private int loyaltyPoints;

    @JsonProperty("Tier")
    private int tier;

    @JsonProperty("Enabled")
    private boolean isEnabled;

    @JsonIgnore
    private List<Rental> rentedMovies;

    @JsonProperty("Is Admin")
    private boolean isAdmin;

    @JsonProperty("Granted Authorities")
    private final Set<GrantedAuthority> authorities = new HashSet<>();*/

    private int userID, loyaltyPoints;
    private String username, password, authority;
    private boolean isAccountNonLocked, isEnabled;
    private boolean isAdmin;
    private int tier;
    private List<Rental> rentedMovies = Collections.EMPTY_LIST;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public User(int userID, String username, String password, String authority, boolean banned) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.authority = authority;
        isAccountNonLocked = !banned;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public int getTier() {
        return tier;
    }

    public List<Rental> getRentedMovies() {
        return rentedMovies;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void setAuthority(String authority) { this.authority = authority;}

    public void setBanned(boolean banned) {
        isAccountNonLocked = !banned;
    }

    public void setRentedMovies(List<Rental> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void stateCheck() {
        int lp = this.getLoyaltyPoints();
        if(lp > 500 && lp < 1500) {
            this.setTier(2);
        } else if (lp > 1500 && lp < 3000) {
            this.setTier(3);
        } else if (lp > 3000) {
            this.setLoyaltyPoints(3000);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                '}';
    }
}