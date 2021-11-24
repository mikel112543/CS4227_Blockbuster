package com.example.movierental.logger;

import org.springframework.stereotype.Service;

/**
 * Author - Michael Danaher
 */
//Client to initiate Logger chaining
@Service
public class RequesterClient {


    public RequesterClient() {
    }

    public static AbstractLogger getChaining() {

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR_INFO);
        AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG_INFO);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.OUTPUT_INFO);

        // errorLogger -> debugLogger
        // debugLogger -> consoleLogger
        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }
}
