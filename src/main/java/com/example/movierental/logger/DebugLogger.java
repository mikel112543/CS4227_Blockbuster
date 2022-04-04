package com.example.movierental.logger;

import org.slf4j.Logger;

/**
 * Author - Michael Danaher
 */
//Logger Type 2 - Debug Logger
public class DebugLogger implements LoggerInterceptor {

    @Override
    public void logMessage(Logger logger, String msg, int type) {
        logger.debug(msg);
    }
}
