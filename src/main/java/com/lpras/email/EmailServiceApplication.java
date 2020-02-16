package com.lpras.email;

import com.lpras.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.abc", "com.lpras.email", "com.lpras.lombok.model"})
public class EmailServiceApplication implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //emailService.sendPreConfiguredMail("Ho ho ho");
        //emailService.testSendMail("lekh565@gmail.com", "Hi", "Ho ho ho send email");
        emailService.sendMail("lekh565@gmail.com","abc@gmail.com","Test email","Hello This is test email","Dear","Pune","lekhraj prasad","7795565234");
    }

}
