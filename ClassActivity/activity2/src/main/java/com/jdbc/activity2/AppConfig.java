package com.jdbc.activity2;

import org.springframework.context.annotation.Bean;
import com.jdbc.activity2.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public GreetingService greetingService(){
        return new GreetingService();
    }

}
