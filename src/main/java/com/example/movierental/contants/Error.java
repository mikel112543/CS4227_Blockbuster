package com.example.movierental.contants;

/**
 * Author - Michael Danaher
 */
//Enum class to hold unchangeable error messages and Codes
public enum Error {

    GENERAL_ERROR("2000", "General Error occurred"),
    PAYMENT_DECLINED("2001", "Payment was unfortunately declined"),
    INVALID_USER_ID("2002", "Invalid user ID"),
    INVALID_MOVIE_ID("2003", "Invalid movie ID"),
    INVALID_LOGIN("2004", "Invalid Login please try again");

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
