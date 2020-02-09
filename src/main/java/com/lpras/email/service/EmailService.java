package com.lpras.email.service;

public interface EmailService {
    public void sendPreConfiguredMail(String message);
    public void sendMail(String to, String subject, String body);
}
