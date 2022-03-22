package com.example.movierental.logger;

import org.slf4j.Logger;

/**
 * Author - Michael Danaher
 */
//Logger Type 1 - Error Logger
public class ErrorLogger implements LoggerInterceptor {

    @Override
    public void logMessage(Logger logger, String msg, int type) {
        logger.error(msg);
    }
}
