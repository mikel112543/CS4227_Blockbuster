package com.example.movierental.logger;

import org.slf4j.Logger;

public interface LoggerInterceptor {

    int ERROR = 0;
    int DEBUG = 1;
    int INFO = 2;

    void logMessage(Logger logger, String msg, int type);
}
