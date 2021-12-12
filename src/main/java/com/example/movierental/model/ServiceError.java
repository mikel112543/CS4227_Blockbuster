package com.example.movierental.model;

import com.example.movierental.contants.Error;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author - Michael Danaher
 */

//Error Model
public class ServiceError {

    @JsonProperty("Error Code")
    private String errorCode;

    @JsonProperty("Error Message")
    private String errorMessage;

    //Enum as argument
    public ServiceError(Error error) {
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
