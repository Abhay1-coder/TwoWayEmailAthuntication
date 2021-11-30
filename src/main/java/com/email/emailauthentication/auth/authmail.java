package com.email.emailauthentication.auth;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authmail {
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send/{email}")//POST request
    String sendEmail(@PathVariable String email){// pathvariable will take data from url email= email
        SimpleMailMessage msg = new SimpleMailMessage();//Models a simple mail message, including data such as the from, to, cc, subject, and text fields
        msg.setTo(email);//apply simplemail method to our mail coming from url

        Random random = new Random();
        String n = String.format("%04d", random.nextInt(1000));// it will take four digit random number 

        msg.setSubject("Authenticated hahahaha");// setting subject
        msg.setText("your code is:" +n);// setting text in mail.. the number we have genetated ramdomly

        try{
            javaMailSender.send(msg);// now sending message
            return n+""; 
        }catch(Exception e){
            return e.getMessage();
        }
    }
    
}
