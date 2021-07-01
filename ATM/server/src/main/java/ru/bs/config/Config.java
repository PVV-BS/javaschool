package ru.bs.config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.bs.controller.AtmWebController;

@Configuration
@ComponentScan(basePackages = {"ru.bs"})
public class Config {
    @Bean
    public Logger getLogger() {

        Logger logger = LoggerFactory.getLogger(AtmWebController.class);
        return logger;
    }
}
