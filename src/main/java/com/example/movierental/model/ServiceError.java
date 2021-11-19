package com.example.movierental.model;
import com.example.movierental.contants.Error;

/**
 * Author - Michael Danaher
 */

//Error Model
public class ServiceError {

    private String errorCode;
    private String errorMessage;

    //Enum as argument
    public ServiceError(Error error) {
        this.errorCode = error.getErrorMessage();
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
