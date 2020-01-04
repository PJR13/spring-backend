package me.paulojr.spbk.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);


    @Override
    public void sendMail(SimpleMailMessage msg) {
        log.info("Envio de email...");
        mailSender.send(msg);
        log.info("Email enviado.");

    }
}
