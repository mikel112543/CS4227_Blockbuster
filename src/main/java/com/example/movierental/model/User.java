package com.example.movierental.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class User implements UserDetails {

    private int userID, loyaltyPoints, tier;
    private String username, password, authority;
    private boolean isAccountNonLocked, isEnabled;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public User() {}

    public User(int userID, int loyaltyPoints, int tier, String username, String password, String authority, boolean isAccountNonLocked) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.loyaltyPoints = loyaltyPoints;
        this.tier = tier;
        this.isAccountNonLocked = isAccountNonLocked;
        //authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public int getUserID() {
        return userID;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getTier() {
        return tier;
    }

    public String getAuthority() {
        return authority;
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

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void setAuthority(String authority) { this.authority = authority;}

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }


    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", loyaltyPoints=" + loyaltyPoints +
                ", tier=" + tier +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                ", isAccountNonLocked=" + isAccountNonLocked +
                '}';
    }
}
