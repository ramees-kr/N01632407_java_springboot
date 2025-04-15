package com.rm.activity12;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener {

    @JmsListener(destination = "demo-queue")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
        // process message here
    }
}