package com.example.movierental.contants;

/**
 * Author - Michael Danaher
 */
//Enum class to hold unchangeable error messages and Codes
public enum Error {

    INVALID_USER_ID("2002", "Invalid customer ID"),
    INVALID_MOVIE_ID("2003", "Invalid movie ID"),
    INVALID_LOGIN("2004", "Invalid Login please try again"),
    NO_RENTALS("2006", "User has no rentals"),
    ALREADY_RENTING("2007", "You are currently renting this movie"),
    INVALID_USER_RENTALS("2008", "Invalid Rentals"),
    INVALID_MOVIE_NAME("2009", "Can't find movie with search, search for another movie isntead"),
    INVALID_PRICE_CODE("2010","Incorrect price code entered: Must be one of 0,1,2");


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
