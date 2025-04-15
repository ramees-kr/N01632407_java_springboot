package com.rm.activity12;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener {

    @JmsListener(destination = "demo-queue")
    public void receiveMessage( MessageParser message) {
        System.out.println("Received: " );
        System.out.println("Name: "+ message.getName());
        System.out.println("Message: " + message.getMessage());

    }
}