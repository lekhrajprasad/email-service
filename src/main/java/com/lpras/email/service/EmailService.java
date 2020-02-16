package com.lpras.email.service;

public interface EmailService {
    //public void sendPreConfiguredMail(String message);
    public void testSendMail(String to, String subject, String body);
    public void sendMail (String to, String from, String subject, String bodyContent, String toName, String fromLocation, String fromSignaure, String phone);
}
