package com.example.movierental.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author - Michael Danaher
 */
//Logger Type 3 - Error Logger
public class ErrorLogger extends AbstractLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorLogger.class);

    public ErrorLogger(int levels) {
        this.levels = levels;
    }

    @Override
    protected void showLogMsg(String msg) {
        LOGGER.error(msg);
    }
}
