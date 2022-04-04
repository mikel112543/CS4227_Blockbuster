package com.example.movierental.logger;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author - Michael Danaher
 */
//Client to dispatch interceptors
@Service
public class Dispatcher implements LoggerInterceptor {

    private final List<LoggerInterceptor> interceptors;


    public Dispatcher() {
        interceptors = new ArrayList<>();
        interceptors.add(new ErrorLogger());   //0
        interceptors.add(new DebugLogger());   //1
        interceptors.add(new ConsoleLogger()); //2
    }

    @Override
    public void logMessage(Logger logger, String msg, int type) {
        switch (type) {
            case ERROR:
                interceptors.get(ERROR).logMessage(logger, msg, type);
                break;
            case DEBUG:
                interceptors.get(DEBUG).logMessage(logger, msg, type);
                break;
            case INFO:
                interceptors.get(INFO).logMessage(logger, msg, type);
                break;
            default:
                for (LoggerInterceptor interceptor : interceptors) {
                    interceptor.logMessage(logger, msg, type);
                }
                break;
        }
    }
}
