package com.jdbc.activity2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Scope("singleton")
    public GreetingService greetingService() {
        return new GreetingService();
    }
}
