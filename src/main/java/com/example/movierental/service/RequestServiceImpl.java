package com.example.movierental.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class RequestServiceImpl implements RequestService{
    @Override
    public String getClientIPAddress(HttpServletRequest request) {
        return null;
    }
}
