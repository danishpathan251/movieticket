package com.movieticket.controller;

import com.movieticket.dao.UserRepository;
import com.movieticket.entities.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    public UserRepository userRepository;
    private final JavaMailSender emailSender;

    public EmailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    @PostMapping("/sendEmail")
    @CrossOrigin(origins = "*")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getRecipient());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getContent());
        emailSender.send(message);
    }
//    public String Example(){
//        return "this is good";
//    }
@PostMapping("/sendOtpEmail")
@CrossOrigin(origins = "*")
public void sendOtpEmail(@RequestBody EmailRequest emailRequest) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(emailRequest.getRecipient());
    message.setSubject(emailRequest.getSubject());
    message.setText(emailRequest.getContent());
    emailSender.send(message);
}

    @PostMapping("/sendOfferEmail")
    @CrossOrigin(origins = "*")
    public void sendOfferEmail(@RequestBody EmailRequest emailRequest) {
        // Create a SimpleMailMessage instance
        SimpleMailMessage message = new SimpleMailMessage();

        // Set the recipient email address
        message.setTo(emailRequest.getRecipient());

        // Set the email subject
        message.setSubject(emailRequest.getSubject());

        // Set the email content (HTML content)
        message.setText(emailRequest.getContent());

        // Send the email using the emailSender (assuming you have it configured)
        emailSender.send(message);
    }


    @GetMapping("/findAll")
    public List<String> findAllUser(){
        List<String> emails = userRepository.findAllEmail();

        String content = "New Offer Arrive";
        for(String email: emails){
//            System.out.println(email);
            SimpleMailMessage message = new SimpleMailMessage();

            // Set the recipient email address
            message.setTo(email);

            // Set the email subject
            message.setSubject("MOVIE TICKET BOOKING: OFFERS");

            // Set the email content (HTML content)
            message.setText(content);

            // Send the email using the emailSender (assuming you have it configured)
            emailSender.send(message);

        }
        return emails;
    }

    @PostMapping("/sendCancelEmail")
    @CrossOrigin(origins = "*")
    public void sendCancelEmail(@RequestBody EmailRequest emailRequest) {
        // Create a SimpleMailMessage instance
        SimpleMailMessage message = new SimpleMailMessage();

        // Set the recipient email address
        message.setTo(emailRequest.getRecipient());

        // Set the email subject
        message.setSubject(emailRequest.getSubject());

        // Set the email content (HTML content)
        message.setText(emailRequest.getContent());

        // Send the email using the emailSender (assuming you have it configured)
        emailSender.send(message);
    }
}
