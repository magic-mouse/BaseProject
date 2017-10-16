package com.github.dronezcc.riser.gui.services;


import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailSendingService {

    private JavaMailSender mailSender;
    private SimpleMailMessage templateMessage;

    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();



        mailSender.setHost("localhost");
        mailSender.setPort(2525);

        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }


    public void sendPasswordResetMail(String email) {
        this.mailSender = getJavaMailSender();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setText("There should be some text here ... but there inst... Psyke!");
        msg.setSubject("Reset Mail");
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }

    }
}
