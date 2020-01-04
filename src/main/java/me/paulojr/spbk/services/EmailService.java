package me.paulojr.spbk.services;

import me.paulojr.spbk.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmEmail(Pedido obj);

    void sendMail(SimpleMailMessage msg);
}
