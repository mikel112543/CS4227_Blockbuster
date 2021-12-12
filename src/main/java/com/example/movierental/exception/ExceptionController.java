package com.example.movierental.exception;

import com.example.movierental.model.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Author - Michael Danaher
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST) // Setting status code
@RestControllerAdvice //Controller Advice to globalise Exception Handling
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ServiceException.class})
    protected ServiceError handleException(ServiceException e) {
        return e.getServiceError();
    }

}
