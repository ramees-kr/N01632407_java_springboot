package com.jdbc.activity2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingPrinter {

    private final GreetingService greetingService;

    @Autowired
    public GreetingPrinter(GreetingService greetingService){
        this.greetingService=greetingService;
    }

    public void print(){
        System.out.println("Printer printing");
    }
}
