package me.paulojr.spbk.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);


    @Override
    public void sendMail(SimpleMailMessage msg) {
        log.info("Envio de email...");
        mailSender.send(msg);
        log.info("Email enviado.");

    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Envio de email...");
        javaMailSender.send(msg);
        log.info("Email enviado.");
    }
}
