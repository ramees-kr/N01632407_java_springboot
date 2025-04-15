package com.example.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;

@Stateless
public class LoggerBean {
    @PostConstruct
    public void init() {
        System.out.println("Initializing LoggerBean");
    }

    public void log(String message) {
        System.out.println(message);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying LoggerBean");
    }
}
