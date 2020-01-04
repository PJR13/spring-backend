package me.paulojr.spbk.config;

import me.paulojr.spbk.services.DbService;
import me.paulojr.spbk.services.EmailService;
import me.paulojr.spbk.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DbService dbService;

    @Bean
    public boolean initDatabase() throws ParseException {
        dbService.initDB();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }

}
