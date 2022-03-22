package com.example.movierental.logger;

import org.slf4j.Logger;

/**
 * Author - Michael Danaher
 */
//Logger Type 3 - Console Logger
public class ConsoleLogger implements LoggerInterceptor {

    @Override
    public void logMessage(Logger logger, String msg, int type) {
        logger.info(msg);
    }
}
