package com.lpras.email.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig {

    @Bean
    public SimpleMailMessage emailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("lekh565@gmail.com");
        //message.setFrom("lekh@gmail.com");
        message.setSubject("Important email 2");
        message.setText("FATAL - Application crash. Save your job !!");
        return message;
    }
}
