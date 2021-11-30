package com.example.movierental.exception;

import com.example.movierental.model.ServiceError;

/**
 * Author - Michael Danaher
 */
//Custom Exception Handler
public class ServiceException extends RuntimeException {

    private final transient ServiceError serviceError;

    public ServiceException(ServiceError serviceError) {
        this.serviceError = serviceError;
    }

    public ServiceError getServiceError() {
        return this.serviceError;
    }
}
