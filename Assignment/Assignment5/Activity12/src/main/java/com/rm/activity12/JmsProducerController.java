package com.rm.activity12;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JmsProducerController {

    private final JmsTemplate jmsTemplate;

    public JmsProducerController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageParser message) {
        jmsTemplate.convertAndSend("demo-queue", message);
        return "Message sent ";
    }
}
