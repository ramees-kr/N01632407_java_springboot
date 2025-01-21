package com.jdbc.activity2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class GreetingService {
    public String helloBeans() {
        return "Hello, Spring Beans!";
    }

    @PostConstruct
    public void init() {
        System.out.println("GreetingService initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("GreetingService destroyed");
    }
}
