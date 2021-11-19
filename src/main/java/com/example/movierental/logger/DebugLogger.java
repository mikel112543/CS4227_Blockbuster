package com.example.movierental.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author - Michael Danaher
 */
//Logger Type 2 - Debug Logger
public class DebugLogger extends AbstractLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugLogger.class);

    public DebugLogger(int levels) {
        this.levels = levels;
    }

    /**
     * @param msg Message to be displayed by the logger
     */
    @Override
    protected void showLogMsg(String msg) {
        LOGGER.debug(msg);
    }
}
