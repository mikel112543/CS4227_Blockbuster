package com.example.movierental.model;

import com.example.movierental.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static com.example.movierental.security.UserRole.ADMIN;
import static com.example.movierental.security.UserRole.USER;

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
    private List<Rental> rentedMovies = new ArrayList<>();

    @JsonIgnore
    private boolean isAdmin;

    @JsonProperty
    private boolean isAccountNonLocked;

    @JsonProperty
    private boolean discount;

    @JsonIgnore
    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int userID, String username, String password, String authority, boolean banned) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        isAccountNonLocked = !banned;
        tier = 1;
        if (authority.equals("ROLE_USER")) {
            authorities = USER.getGrantedAuthorities();
        } else if (authority.equals("ROLE_ADMIN")) {
            authorities = ADMIN.getGrantedAuthorities();
            isAdmin = true;
        }
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
        return authorities;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
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

    public void setAuthorities(Set<SimpleGrantedAuthority> grantedAuthorities) {
        authorities = grantedAuthorities;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void setBanned(boolean banned) {
        isAccountNonLocked = !banned;
    }

    public boolean isBanned() {
        return !isAccountNonLocked;
    }

    public void setRentedMovies(List<Rental> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void stateCheck() {
        int lp = this.getLoyaltyPoints();
        if (lp >= 500 && lp <= 1499) {
            this.setTier(2);
        } else if (lp >= 1500 && lp <= 2999) {
            this.setTier(3);
        } else if (lp > 3000) {
            this.setLoyaltyPoints(3000);
            this.setTier(3);
        } else {
            this.setTier(1);
        }
    }

    public boolean hasRole(UserRole Role) {
        if(this.authorities.equals(Role.getGrantedAuthorities())) {
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                '}';
    }
}