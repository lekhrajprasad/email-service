package com.lpras.email;

import com.lpras.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //emailService.sendPreConfiguredMail("Ho ho ho");
        emailService.sendMail("lekh565@gmail.com", "Hi", "Ho ho ho send email");
    }

}
