package com.jdbc.activity2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    @PostConstruct
    public void init() {
        System.out.println("Greetings Service initialized");
    }

    public void greeting(){
        System.out.println("Hello, Spring Beans!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Greetings Service destroyed");
    }
}
