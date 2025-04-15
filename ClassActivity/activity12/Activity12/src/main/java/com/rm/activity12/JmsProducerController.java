package com.rm.activity12;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JmsProducerController {

    private final JmsTemplate jmsTemplate;

    public JmsProducerController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        jmsTemplate.convertAndSend("demo-queue", message);
        return "Message sent: " + message;
    }
}
