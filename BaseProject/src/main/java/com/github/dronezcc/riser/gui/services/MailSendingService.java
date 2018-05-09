package com.github.dronezcc.riser.gui.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailSendingService {

    private JavaMailSender mailSender;

    @Value("${application.mail.host}")
    private String host;
    @Value("${application.mail.port}")
    private int port;
    @Value("${application.mail.username")
    private String userName;
    @Value("${application.mail.password}")
    private String password;

    private JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);

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
    }


    public void sendPasswordResetMail(String email, String link, String token) {
        this.mailSender = getJavaMailSender();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setText("There should be some text here ... but there inst... Psyke!");
        msg.setText("Link: " + link + "?token=" + token);
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
