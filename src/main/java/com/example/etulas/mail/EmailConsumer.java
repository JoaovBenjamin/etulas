package com.example.etulas.mail;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;

@Component
public class EmailConsumer {
    private final EmailService emailService;

    public EmailConsumer(EmailService emailService){
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email-queue")
    public void sendEmail(String message) throws MessagingException{
        emailService.sendEmail(message);
    }
}
