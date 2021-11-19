package com.example.movierental.exception;

import com.example.movierental.model.ServiceError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Author - Michael Danaher
 */
@ControllerAdvice //Controller Advice to globalise Exception Handling
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ServiceException.class})
    protected ServiceError handleException(ServiceException e) {
        return e.getServiceError();
    }

}
