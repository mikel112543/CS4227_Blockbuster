package com.example.movierental.service;


public interface MailService {
    public void sendEmail(String toEmail, String subject, String body);
}