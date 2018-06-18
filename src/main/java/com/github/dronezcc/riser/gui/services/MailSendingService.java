package com.github.dronezcc.riser.gui.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailSendingService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
    }

    public void sendPasswordResetMail(String email, String link, String token) {


        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setText("There should be some text here ... but there inst... Psyke!");
        msg.setText("Link: " + link + "?token=" + token);
        msg.setSubject("Reset Mail");
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            log.error(ex.getMessage(),ex);
        }

    }
}
