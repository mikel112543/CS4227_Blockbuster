package com.example.movierental.logger;

/**
 * Author - Michael Danaher
 */
//CHAIN OF RESPONSIBILITY DESIGN PATTERN
public abstract class AbstractLogger {
    public static int OUTPUT_INFO=1;
    public static int ERROR_INFO=2;
    public static int DEBUG_INFO=3;

    protected int levels;

    protected AbstractLogger nextAbstractLogger;

    public void setNextLogger(AbstractLogger nextAbstractLogger) {
        this.nextAbstractLogger = nextAbstractLogger;
    }

    public void logMessage(int levels, String msg) {
        if(this.levels <= levels) {
            showLogMsg(msg);
        }
        if(nextAbstractLogger != null) {
            nextAbstractLogger.logMessage(levels, msg);
        }
    }
    protected abstract void showLogMsg(String msg);
}
