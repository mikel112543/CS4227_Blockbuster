package com.example.movierental.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author - Michael Danaher
 */
//Logger Type 1 - Console Logger
public class ConsoleLogger extends AbstractLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleLogger.class);

    public ConsoleLogger(int levels) { this.levels = levels; }

    @Override
    protected void showLogMsg(String msg) {
        LOGGER.info(msg);
    }
}
