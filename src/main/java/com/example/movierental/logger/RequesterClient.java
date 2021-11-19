package com.example.movierental.logger;

/**
 * Author - Michael Danaher
 */
//Client to initiate Logger chaining
public class RequesterClient {

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
