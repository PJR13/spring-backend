package me.paulojr.spbk.services;

import me.paulojr.spbk.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private  TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendOrderConfirmEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailFromPedido(obj);
        sendMail(sm);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj){
        MimeMessage mm = null;
        try {
            mm = prepareMimeMessageFromPedido(obj);
        } catch (MessagingException e) {
            sendOrderConfirmEmail(obj);
        }
        sendHtmlEmail(mm);
    }

    protected String htmlFromTemplatePedido(Pedido obj){
        Context context = new Context();
        context.setVariable("pedido", obj);
        return templateEngine.process("email/confirmPedidos", context);
    }

    protected SimpleMailMessage prepareSimpleMailFromPedido(Pedido obj){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido Confirmado! Código: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
        MimeMessage sm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(sm, true);
        mmh.setTo(obj.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido Confirmado! Código: " + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(obj),true);
        return sm;
    }

}
