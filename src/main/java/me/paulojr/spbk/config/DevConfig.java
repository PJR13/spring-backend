package me.paulojr.spbk.config;

import me.paulojr.spbk.services.DbService;
import me.paulojr.spbk.services.EmailService;
import me.paulojr.spbk.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DbService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

    @Bean
    public boolean initDatabase() throws ParseException {
        if(!strategy.equalsIgnoreCase("create")){
            return true;
        }
        dbService.initDB();
        return true;
    }

}
