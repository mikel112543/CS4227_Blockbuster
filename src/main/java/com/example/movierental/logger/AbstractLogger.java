package com.example.movierental.logger;

/**
 * Author - Michael Danaher
 */
//CHAIN OF RESPONSIBILITY DESIGN PATTERN
public abstract class AbstractLogger {
    public static final int OUTPUT_INFO = 1;
    public static final int ERROR_INFO = 2;
    public static final int DEBUG_INFO = 3;

    protected int levels;

    protected AbstractLogger nextAbstractLogger;

    public void setNextLogger(AbstractLogger nextAbstractLogger) {
        this.nextAbstractLogger = nextAbstractLogger;
    }

    public void logMessage(int levels, String msg) {
        if (this.levels <= levels) {
            showLogMsg(msg);
        }
        if (nextAbstractLogger != null) {
            nextAbstractLogger.logMessage(levels, msg);
        }
    }

    //Abstract method to be Overridden
    protected abstract void showLogMsg(String msg);
}
