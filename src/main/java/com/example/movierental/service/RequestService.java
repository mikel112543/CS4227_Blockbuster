package com.example.movierental.service;
import javax.servlet.http.HttpServletRequest;

public interface RequestService {
    String getClientIPAddress(HttpServletRequest request);
}
