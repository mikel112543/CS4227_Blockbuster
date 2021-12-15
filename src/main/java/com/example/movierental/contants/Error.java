package com.example.movierental.contants;

/**
 * Author - Michael Danaher
 */
//Enum class to hold unchangeable error messages and Codes
public enum Error {

    INVALID_USER_ID("2002", "Invalid customer ID"),
    INVALID_MOVIE_ID("2003", "Invalid movie ID"),
    INVALID_LOGIN("2004", "Invalid Login please try again"),
    INVALID_USERNAME("2005", "Username already exists"),
    INVALID_REGISTER("2006", "Could not register user"),
    NO_RENTALS("2007", "User has no rentals"),
    ALREADY_RENTING("2008", "You are currently renting this movie"),
    INVALID_USER_RENTALS("2009", "Invalid Rentals"),
    INVALID_MOVIE_NAME("2010", "Can't find movie with search, search for another movie isntead"),
    INVALID_LOGIN_CREDENTIALS("2011", "Login Credentials are Incorrect"),
    USER_LOAD_FAILURE("2012", "Failed to load user, Username may be invalid"),
    FILE_NOT_FOUND("2013", "Could not find specified file"),
    INVALID_PRICE_CODE("2013","Incorrect price code entered: Must be one of 0,1,2"),
    INVALID_INITIALIZATION("2014","Failure to Initialize list");


    private String errorCode;
    private String errorMessage;

    Error(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}
